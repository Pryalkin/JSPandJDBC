<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Создать фильм</title>
</head>
<body>
<form action="add">
    <input name="movieName" placeholder="Название фильма"/>
    <br><br>
    <input name="count" type="number" placeholder="Кол-во билетов"/>
    <br></br>
    <input type="submit" value="Submit" />
</form>
<a href="buy">Купить билет</a>
<c:out value="${message}"/>
</body>
</html>