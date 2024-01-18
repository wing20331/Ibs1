package com.example.demo;

import com.example.demo.Logic.Model;
import com.example.demo.Logic.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = ("/getApi"))
public class ServletGet extends HttpServlet {

    Model model = Model.getInstance();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        BufferedReader reader = req.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);

        int id;
        try {
            id = jsonRequest.get("id").getAsInt();
        } catch (Exception e) {
            pw.println("{\"error\": \"не правильный id\"}");
            return;
        }

        if (id == 0) {
            pw.println(generateJsonResponseForAllUsers());
        } else if (id > 0) {
            pw.println(generateJsonResponseForSingleUser(id));
        } else {
            pw.println("{\"error\": \"не правильный id\"}");
        }
    }

    private String generateJsonResponseForAllUsers() {
        String json = gson.toJson(model.getFromList());
        return "{\"users\": " + json + "}";
    }

    private String generateJsonResponseForSingleUser(int id) {
        User user = model.getFromList().get(id);
        if (user != null) {
            String json = gson.toJson(user);
            return "{\"user\": " + json + "}";
        } else {
            return "{\"error\": \"такого юзера нет\"}";
        }
    }
}
