package com.example.demo;

import com.example.demo.Logic.Model;
import com.example.demo.Logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    Model model = Model.getInstance();

    private int size = model.getFromList().size();
    private AtomicInteger counter = new AtomicInteger(size+1);



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine())!= null){
                jb.append(line);
            }
        }catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jsbj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        req.setCharacterEncoding("UTF-8");
        String name = jsbj.get("name").getAsString();
        String surName = jsbj.get("surName").getAsString();
        double salary = jsbj.get("salary").getAsDouble();

        User user = new User(name, surName, salary);
        model.add(user, counter.getAndIncrement());

        resp.setContentType("application/json;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        pw.println(gson.toJson(model.getFromList()));
    }
}
