<%@ page import="com.example.demo.Logic.Model" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Title</title>
</head>
<body>
<br/>

  <h1>Домашняя страница по работе с пользователями</h1>

    Введие ID пользователя

  <br/>

  Доступно: <%
    Model model = Model.getInstance();
    out.print(model.getFromList().size());
    %>


    <form method="get" action="get">
      <label> ID:
        <input type="text" name="id"><br/>
      </label>

      <button type="submit">Поиск</button>
    </form>

     <a href="addUser.html">Добавить юзера</a>

</body>
</html>