package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/math")
public class ServletMath extends HttpServlet {
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Чтение данных JSON-запроса
        BufferedReader reader = request.getReader();

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        // Извлечение параметров из JSON
        double a = jsonObject.getAsJsonPrimitive("a").getAsDouble();
        double b = jsonObject.getAsJsonPrimitive("b").getAsDouble();
        String mathOperation = jsonObject.getAsJsonPrimitive("math").getAsString();

        // Выполнение арифметической операции
        double result = calculateResult(a, b, mathOperation);

        // Подготовка ответа в виде JSON
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("result", result);

        // Отправка ответа
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    private double calculateResult(double a, double b, String mathOperation) {
        switch (mathOperation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    throw new ArithmeticException("На ноль делить нельзя(");
                }
            default:
                throw new IllegalArgumentException("Не правильная операция");
        }
    }
}
