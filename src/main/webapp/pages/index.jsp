<%@ page import="org.example.web_2.Result" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <meta charset="UTF-8">
    <title>Лабораторная №2</title>
</head>
<body>
<table class="structure-table">

    <tr>
        <td class="header" colspan="4">
            <h1 id="header-name">Выполнил: Таратенко Алексей</h1>
            <h1 id="header-group">Группа: P3207</h1>
            <h1 id="header-var">Вариант: 467675</h1>
        </td>
    </tr>

    <form action="${pageContext.request.contextPath}/main" method="get" novalidate data-js-form>
    <tr>

        <td class="change-block">
            <h2 class="change-title">Изменение X</h2>

                <input type="number" id="x" name="x_input" class="x-input" placeholder="Число от -5 до 5" min="-5" max="5" aria-errormessage="x-errors" required>
                <label for="x" class="x-label"></label><br>

                <span class="field-errors" id="x-errors" data-js-form-field-errors></span>

        </td>

        <td class="change-block">
            <h2 class="change-title">Изменение Y</h2>

                <input type="radio" id="y1" name="y_input" value="-4" class="y-input" required>
                <label for="y1" class="y-label">-4</label><br>

                <input type="radio" id="y2" name="y_input" value="-3" class="y-input" required>
                <label for="y2" class="y-label">-3</label><br>

                <input type="radio" id="y3" name="y_input" value="-2" class="y-input" required>
                <label for="y3" class="y-label">-2</label><br>

                <input type="radio" id="y4" name="y_input" value="-1" class="y-input" required>
                <label for="y4" class="y-label">-1</label><br>

                <input type="radio" id="y5" name="y_input" value="0" class="y-input" required>
                <label for="y5" class="y-label">0</label><br>

                <input type="radio" id="y6" name="y_input" value="1" class="y-input" required>
                <label for="y6" class="y-label">1</label><br>

                <input type="radio" id="y7" name="y_input" value="2" class="y-input" required>
                <label for="y7" class="y-label">2</label><br>

                <input type="radio" id="y8" name="y_input" value="3" class="y-input" required>
                <label for="y8" class="y-label">3</label><br>

                <input type="radio" id="y9" name="y_input" value="4" class="y-input" required>
                <label for="y9" class="y-label">4</label><br>

        </td>


        <td class="change-block">
            <h2 class="change-title">Изменение R</h2>

                <input type="radio" id="r1" name="r_input" value="1" class="r-input" required>
                <label for="r1" class="r-label">1.0</label><br>

                <input type="radio" id="r2" name="r_input" value="1.5" class="r-input" required>
                <label for="r2" class="r-label">1.5</label><br>

                <input type="radio" id="r3" name="r_input" value="2" class="r-input" required>
                <label for="r3" class="r-label">2.0</label><br>

                <input type="radio" id="r4" name="r_input" value="2.5" class="r-input" required>
                <label for="r4" class="r-label">2.5</label><br>

                <input type="radio" id="r5" name="r_input" value="3" class="r-input" required>
                <label for="r5" class="r-label">3.0</label><br>



        </td>

        <td class="graph" rowspan="2" >

            <canvas id="canvas" width="400" height="400"></canvas>

            <span class="field-errors" id="r-errors"></span>

        </td>

    </tr>

    <tr>
        <td class="button" colspan="3">
            <button type="submit" id="ready-button">Готово</button>
<%--            <span class="field-errors" id="server-errors"></span>--%>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null && !error.isEmpty()) {
            %>
            <span class="field-errors" id="server-errors">
                    <%= error %>
            </span>
            <%
                }
            %>



        </td>
    </tr>
    </form>

    <tr>
        <td class="button" colspan="4">
            <button type="submit" id="statistic-button">Отправить статистику на почту</button>
        </td>
    </tr>

    <tr>
        <td class="result" colspan="4">
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

                <%
                    List<Result> results = (List<Result>) request.getAttribute("results");
                    if (results != null && !results.isEmpty()) {
                        for (Result row : results) {
                %>
                <tr>
                    <td><%= row.getX() %></td>
                    <td><%= row.getY() %></td>
                    <td><%= row.getR() %></td>
                    <td><%= row.getHit() %></td>
                    <td><%= row.getLocalTime() %></td>
                    <td><%= row.getProcessingTime() %></td>

                    <script>
                        const points = [
                            <%
                                if (!results.isEmpty()) {
                                    for (int i = 0; i < results.size(); i++) {
                                        Result rowToDraw = results.get(i);
                            %>
                            {
                                x: <%= rowToDraw.getX() %>,
                                y: <%= rowToDraw.getY() %>,
                                r: <%= rowToDraw.getR() %>,
                                hit: "<%= rowToDraw.getHit() %>"
                            }<%= (i < results.size() - 1) ? "," : "" %>
                            <%
                                    }
                                }
                            %>
                        ];

                        window.onload = function() {
                            points.forEach(point => {
                                drawPoint(point.x, point.y, point.r, point.hit);
                            });
                        }
                    </script>

                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6">Нет результатов</td>
                </tr>
                <%
                    }
                %>

                </tbody>

            </table>

        </td>

    </tr>

</table>

<script>
    // Определяем contextPath один раз
    const contextPath = '${pageContext.request.contextPath}';
</script>

<script src="${pageContext.request.contextPath}/js/graph.js"></script>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
<script src="${pageContext.request.contextPath}/js/fetchFromCanvas.js"></script>
<script src="${pageContext.request.contextPath}/js/fetchStatistic.js"></script>

</body>

</html>