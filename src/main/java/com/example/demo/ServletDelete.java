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
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {
    Gson gson = new Gson();
    Model model = Model.getInstance();
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        int userId = jsonObject.getAsJsonPrimitive("id").getAsInt();


        Model model = Model.getInstance();
        Map<Integer, User> userMap = model.getFromList();
        userMap.remove(userId);

        String jsonResponse = gson.toJson(userMap);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
