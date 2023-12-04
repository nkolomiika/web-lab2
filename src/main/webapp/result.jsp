<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="web.models.*" %>
<%@ page import="web.managers.PointManager" %>

<!doctype html>
<html lang="ru-RU">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="author" content="Коломиец Никита Сергеевич">
    <meta name="description" content="Веб-программирование: Лабораторная работа №2. Результаты проверки">
    <meta name="keywords" content="ITMO, ИТМО, ПИиКТ, ВТ, Лабораторная работа, Веб-программирование"/>

    <link href="stylesheets/styles.css" rel="stylesheet">
    <link rel="icon" type="image/jpg" href="images/favicon.png">
    <title>Результаты проверки | Веб-программирование</title></head>

<body>
<div class="head">
    <h2 class="flickerPinkNeonText">Веб-программирование, Лабораторная работа №2, Вариант: 521700</h2>
    <h3 class="pinkNeonText">Коломиец Никита Сергеевич, Р3208</h3>
</div>
<div class="footer-result">
    <div id="goBack">
        <a href="${pageContext.request.contextPath}/index.jsp">Вернуться к форме</a>
    </div>
    <% PointManager pointManager = (PointManager) request.getServletContext().getAttribute("points");%>
    <table id="outputTable">
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Точка входит в ОДЗ</th>
        </tr>
        <% for (PointData point : pointManager.getPoints()) { %>
        <tr>
            <td>
                <%= point.getPoint().getX() %>
            </td>
            <td>
                <%= point.getPoint().getY() %>
            </td>
            <td>
                <%= point.getRadius() %>
            </td>
            <td>
                <%= point.getStatus() ? "<span>Попал</span>"
                        : "<span>Промазал</span>" %>
            </td>
        </tr>
        <% } %>
    </table>
</div>
<script src="js/script.js"></script>
</body>
</html>
