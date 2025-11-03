<%@ page import="org.example.web_2.Result" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/result-styles.css">
    <meta charset="UTF-8">
    <title>Лабораторная №2</title>
</head>
<body>
<div>
    <h1>Результирующая таблица</h1>

    <table id="result-table">

        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Попадание</th>
            <th>Текущее время</th>
            <th>Время работы скрипта (мс)</th>
        </tr>

        <tbody>
            <% Result result = (Result) request.getAttribute("result"); %>
            <tr>
                <td> <%= result.getX() %></td>
                <td> <%= result.getY() %></td>
                <td> <%= result.getR() %></td>
                <td> <%= result.getHit() %></td>
                <td> <%= result.getLocalTime() %></td>
                <td> <%= result.getProcessingTime() %></td>
            </tr>
        </tbody>

    </table>

    <canvas id="canvas" width="400" height="400"></canvas>

    <script>

        const x = <%= result.getX() %>;
        const y = <%= result.getY() %>;
        const r = <%= result.getR() %>;
        const hit = "<%= result.getHit() %>";


        window.onload = function() {
            drawPoint(x, y, r, hit)
        }

    </script>

    <div>
        <form action="${pageContext.request.contextPath}/result" method="post">
            <button type="submit" id="back-button">Назад</button>
        </form>
    </div>

</div>

<script src="${pageContext.request.contextPath}/js/graph.js"></script>

</body>

</html>
