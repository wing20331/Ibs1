package com.example.demo;

import com.example.demo.Logic.Model;
import com.example.demo.Logic.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/put")
public class ServletPut extends HttpServlet {

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        int userId = jsonObject.getAsJsonPrimitive("id").getAsInt();
        String firstName = jsonObject.getAsJsonPrimitive("firstName").getAsString();
        String lastName = jsonObject.getAsJsonPrimitive("lastName").getAsString();
        double salary = jsonObject.getAsJsonPrimitive("salary").getAsDouble();


        Model model = Model.getInstance();
        Map<Integer, User> userMap = model.getFromList();

        if (userMap.containsKey(userId)) {
            User updatedUser = new User(firstName, lastName, salary);
            userMap.put(userId, updatedUser);
        }


        String jsonResponse = gson.toJson(userMap);


        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
