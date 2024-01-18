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
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        if(id==0){
            pw.println(
                    "<html>" + "<h2>Доступные пользователи</h2>"+
                     "ID пользователя: " +
                     "<ul>");

            for(Map.Entry<Integer,User> entry : model.getFromList().entrySet()){
                pw.println("<li>" + entry.getKey() + "</li>" +
                           "<ul>" +
                           "<li>Имя: " + entry.getValue().getName() + "</li>" +
                           "<li>Фамилия: " + entry.getValue().getSurName() + "</li>" +
                           "<li>зарплата: " + entry.getValue().getSalary() + "</li>"
                            + "</ul>");

            }
            pw.println("</ul>" +
                    "<a href=\"index.jsp\">Домой</a>"
                    + "</html>");
        }else if (id>0){
            if (id > model.getFromList().size()){
                pw.println("<html>" +
                        "<h3>такого пользователя нет</h3>" + "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            }else {
                pw.println("<html>" +
                        "<h3>Запрошенный пользователь</h3>" +
                                "<ul>" +
                                "<li>Имя: " + model.getFromList().get(id).getName() + "</li>" +
                                "<li>Фамилия: " + model.getFromList().get(id).getSurName() + "</li>" +
                                "<li>зарплата: " + model.getFromList().get(id).getSalary() + "</li>"
                                + "</ul>"+
                        "</html>");
            }
        }else {
            pw.println("<html>" +
                    "<h3>такого пользователя нет</h3>" + "<a href=\"index.jsp\">Домой</a>" +
                    "</html>");
        }
    }



}
