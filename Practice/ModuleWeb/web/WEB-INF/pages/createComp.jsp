<%--
  Created by IntelliJ IDEA.
  User: tishort
  Date: 17.09.18
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить комплекутющие</title>
</head>
<body>
<p>Добавить комплекутющие</p>
<div>
<spring:form method="post" modelAttribute="comp" action="addComp">
    <spring:label path="name">Наименование:</spring:label>
    <spring:input path="name" />

    <spring:label path="count">Количество:</spring:label>
    <spring:input path="count" />

    <!-- Разобраться с checkbox-->
    <spring:checkbox path="necessary" value="true" />
    <input type="submit" value="добавить">

</spring:form>
</div>
</body>
</html>
