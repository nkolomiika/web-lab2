<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="web.managers.PointManager" %>
<%@ page import="web.models.*" %>

<!doctype html>
<html lang="ru-RU">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="author" content="Коломиец Никита Сергеевич">
    <meta name="description" content="Веб-программирование: Лабораторная работа №2.">
    <meta name="keywords" content="ITMO, ИТМО, ПИиКТ, ВТ, Лабораторная работа, Веб-программирование"/>

    <link href="stylesheets/styles.css" rel="stylesheet">
    <link rel="icon" type="image/jpg" href="images/favicon.png">
    <link id="notify" rel="stylesheet" type="text/css" href="https://unpkg.com/notie/dist/notie.min.css">
    <title>Лабораторная работа №2 | Веб-программирование</title>
</head>

<body>
<div>
    <div class="head">
        <h2 class="flickerPinkNeonText">Веб-программирование, Лабораторная работа №2, Вариант: 521700</h2>
        <h3 class="pinkNeonText">Коломиец Никита Сергеевич, Р3208</h3>
    </div>

    <div>
        <div class="main">
            <div>
                <div class="title-header">
                    <h3 class="title">Валидация значений</h3>
                </div>
                <div class="buttons">
                    <div>
                        <div class="value-tittle-X">Выберите Х:</div>
                        <div class="block-X">
                            <div>
                                <input class="button" name="X-button" type="button" value="-2"
                                       onclick="setX(this.value)">
                                <input class="button" name="X-button" type="button" value="-1.5"
                                       onclick="setX(this.value)">
                                <input class="button" name="X-button" type="button" value="-1"
                                       onclick="setX(this.value)">
                            </div>
                            <div>
                                <input class="button" name="X-button" type="button" value="-0.5"
                                       onclick="setX(this.value)">
                                <input class="button" name="X-button" type="button" value="0"
                                       onclick="setX(this.value)">
                                <input class="button" name="X-button" type="button" value="0.5"
                                       onclick="setX(this.value)">
                            </div>
                            <div>
                                <input class="button" name="X-button" type="button" value="1"
                                       onclick="setX(this.value)">
                                <input class="button" name="X-button" type="button" value="1.5"
                                       onclick="setX(this.value)">
                                <input class="button" name="X-button" type="button" value="2"
                                       onclick="setX(this.value)">
                                <input type="hidden" id="X" value="">
                            </div>
                        </div>
                    </div>
                    <div class="block-Y">
                        <div class="value-tittle">Введите Y:</div>
                        <div>
                            <input required type="text" name="Y-input"
                                   placeholder="-3...3" maxlength="6"
                                   pattern="^-[0-3]|[0-3]" class="textBorderPink">
                        </div>
                    </div>
                    <div class="block-R">
                        <div class="value-tittle">Введите R:</div>
                        <div class="radio-R">
                            1<input type="radio" name="R-radio-group" value="1">
                            1.5<input type="radio" name="R-radio-group" value="1.5">
                            2<input type="radio" name="R-radio-group" value="2">
                            2.5<input type="radio" name="R-radio-group" value="2.5">
                            3<input type="radio" name="R-radio-group" value="3">
                        </div>
                    </div>
                    <div>
                        <button type="submit" id="checkButton" onclick="validation()">Проверить</button>
                    </div>
                </div>
                <div>
                    <svg id="svg" width="400" height="400" xmlns="http://www.w3.org/2000/svg">
                        <polygon points="200,200 200,50 350,200" class="graphic"></polygon>
                        <polygon points="200,200 125,200 125,350 200,350" class="graphic"></polygon>
                        <path d="M 200 275 A 75 75, 1, 0, 0, 275 200 L 200 200" class="graphic"/>
                        <line x1="0" y1="200" x2="400" y2="200" stroke="black"/>
                        <line x1="200" y1="0" x2="200" y2="400" stroke="black"/>

                        <line x1="400" y1="200" x2="390" y2="195" stroke="black"/>
                        <line x1="400" y1="200" x2="390" y2="205" stroke="black"/>
                        <line x1="200" y1="0" x2="205" y2="10" stroke="black"/>
                        <line x1="200" y1="0" x2="195" y2="10" stroke="black"/>

                        <text x="385" y="215">x</text>
                        <text x="215" y="15">y</text>

                        <text x="40" y="185">-R</text>
                        <line x1="50" y1="195" x2="50" y2="205" stroke="black"/>
                        <text x="115" y="185">-R/2</text>
                        <line x1="125" y1="195" x2="125" y2="205" stroke="black"/>
                        <text x="270" y="185">R/2</text>
                        <line x1="275" y1="195" x2="275" y2="205" stroke="black"/>
                        <text x="345" y="185">R</text>
                        <line x1="350" y1="195" x2="350" y2="205" stroke="black"/>

                        <text x="215" y="50">R</text>
                        <line x1="195" y1="50" x2="205" y2="50" stroke="black"/>
                        <text x="215" y="125">R/2</text>
                        <line x1="195" y1="125" x2="205" y2="125" stroke="black"/>
                        <text x="215" y="275">-R/2</text>
                        <line x1="195" y1="275" x2="205" y2="275" stroke="black"></line>
                        <text x="215" y="350">-R</text>
                        <line x1="195" y1="350" x2="205" y2="350" stroke="black"></line>
                    </svg>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <%  int prev = 0;
        int now = 0;
        PointManager pointManager = (PointManager) request.getServletContext().getAttribute("points");
        if (pointManager == null) {
    %>

    <h3 id="no-results">
        <span class="notification">Нет результатов</span>
    </h3>
    </h4>
    <table id="outputTable">
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Точка входит в ОДЗ</th>
        </tr>
    </table>

    <% } else {
        prev = (int) request.getServletContext().getAttribute("previous_size");
        now = (int) request.getServletContext().getAttribute("size");%>
    <table id="outputTable">
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Точка входит в ОДЗ</th>
        </tr>
        <% if (now - prev > 0) {
            for (PointData point : pointManager.getPoints()) { %>
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
    <% }
    }%>
</div>
</body>
<script src="https://unpkg.com/notie"></script>
<script
        src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
<script src="js/script.js"></script>
</html> 