<%--
  Created by IntelliJ IDEA.
  User: tishort
  Date: 15.09.18
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Список комплектующих</title>
</head>
<body>
<h1>Список комплектующих</h1>


<table border="1">
    <tr>
        <td>ID</td>
        <td>Наименование</td>
        <td>Необходимость</td>
        <td>Количество</td>
        <td>Редактирвоать</td>
        <td>Удалить</td>
    </tr>
    <c:forEach items="${listComps}" var="list">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <td>${list.necessary}</td>
            <td>${list.count}</td>
            <td><a href="/update/${list.id}">Update</a></td>
            <td><a href="/delete/${list.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<p>
    <a href="/addComp">Добавить комплектующие</a>
</p>




</body>
</html>
