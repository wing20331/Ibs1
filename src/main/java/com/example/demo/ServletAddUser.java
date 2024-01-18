package com.example.demo;

import com.example.demo.Logic.Model;
import com.example.demo.Logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/addUser")
public class ServletAddUser extends HttpServlet {
    Model model = Model.getInstance();

    private int size = model.getFromList().size();
    private AtomicInteger counter = new AtomicInteger(size+1);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        request.setCharacterEncoding("UTF-8");

        PrintWriter pw = response.getWriter();

        String name = request.getParameter("name");
        String surName = request.getParameter("surName");
        double salary = Double.parseDouble(request.getParameter("salary"));

        User user = new User(name, surName, salary);
        model.add(user, counter.getAndIncrement());

        pw.println("<html>" +
                "<h1>пользователь успешно создан</h1>" +
                "<a href=\"addUser.html\">Создать нового пользователя</a> <br/>" +
                "<a href=\"index.jsp\">Домой</a>" + "<html>");
    }
}
