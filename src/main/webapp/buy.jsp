<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cov" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <caption>Таблица фильмов и наличия билетов</caption>
    <tr>
        <th>Название фильма</th>
        <th>Кол-во билетов</th>
        <th>Купить кол-во билетов</th>
    </tr>
<%--    <jsp:useBean id="cinemas" scope="request" type="java.util.List"/>--%>
    <jsp:useBean id="cinemas" scope="request" type="java.util.List"/>
    <cov:forEach var="cinema" items="${cinemas}">
        <tr>
            <td><cov:out value="${cinema.getMovieName()}"/></td>
            <td><cov:out value="${cinema.getCount()}"/></td>
            <td><form action="buy">
                <input name="count" placeholder="Купить кол-во"/>
                <input name="id" type="hidden" value="<cov:out value="${cinema.getId()}"/>"/>
                <input type="submit" value="Купить" />
            </form></td>
        </tr>
    </cov:forEach>
</table>
<c:out value="${message}"/>
<a href="index.jsp">Создать фильм</a>
</body>
</html>
