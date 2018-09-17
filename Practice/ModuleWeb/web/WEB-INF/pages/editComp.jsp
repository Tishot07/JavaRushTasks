<%--
  Created by IntelliJ IDEA.
  User: tishort
  Date: 17.09.18
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Обновить данные</title>
</head>
<body>

<p>Обновить комплекутющие</p>
<div>
    <spring:form method="post" modelAttribute="comp" action="update">

        <spring:label path="id">ID:</spring:label><br />
        <spring:input path="id" /><br /><br />

        <spring:label path="name">Наименование:</spring:label><br />
        <spring:input path="name" /><br /><br />

        <spring:label path="count">Количество:</spring:label><br />
        <spring:input path="count" /><br /><br />

        <!-- Разобраться с checkbox-->
        <spring:checkbox path="necessary" value="true" /><br /><br />
        <input type="submit" value="Обновить">

    </spring:form>
</div>

</body>
</html>
