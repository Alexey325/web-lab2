package org.example.web_2.Servlets;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.web_2.Result;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet("/main")
public class ControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        long startTime = System.nanoTime();


        ServletContext context = getServletContext();
        List<Result> results = (List<Result>) context.getAttribute("results");

        if (results == null) {
            results = new ArrayList<>();
            context.setAttribute("results", results);
        }

        request.setAttribute("results", results);


        String x = request.getParameter("x_input");
        String y = request.getParameter("y_input");
        String r = request.getParameter("r_input");

        if (x == null && y == null && r == null) {
            request.getRequestDispatcher("pages/index.jsp").forward(request, response);
            return;
        }

        if (x == null || y == null || r == null) {
            response.setStatus(400);
            request.setAttribute("error", "Заполните все поля формы");
            request.getRequestDispatcher("pages/index.jsp").forward(request, response);
            return;
        }

        try {
            double xValue = Double.parseDouble(x);
            double yValue = Double.parseDouble(y);
            double rValue = Double.parseDouble(r);

            if (xValue < -5 || xValue > 5) {
                response.setStatus(422);
                request.setAttribute("error", "X должен принимать значения от -5 до 5");
                request.getRequestDispatcher("pages/index.jsp").forward(request, response);
                return;
            }

            if (!Set.of(1.0, 1.5, 2.0, 2.5, 3.0).contains(rValue)) {
                response.setStatus(422);
                request.setAttribute("error", "R должен принимать значения из radio");
                request.getRequestDispatcher("pages/index.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();

            session.setAttribute("x", x);
            session.setAttribute("y", y);
            session.setAttribute("r", r);
            session.setAttribute("processingTime", startTime);
            session.setAttribute("requestProcessed", false);

            response.sendRedirect(request.getContextPath() + "/result");


        } catch (NumberFormatException e) {
            response.setStatus(400);
            request.setAttribute("error", "Неверный формат чисел");
            request.getRequestDispatcher("pages/index.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String jsonData = sb.toString();
        System.out.println("Received JSON: " + jsonData);

        JSONObject jsonObject = new JSONObject(jsonData);

        String attempts = String.valueOf(jsonObject.getInt("attempts"));
        String hits = String.valueOf(jsonObject.getInt("hits"));
        String misses = String.valueOf(jsonObject.getInt("misses"));
        String percentage = String.valueOf(jsonObject.getInt("percentage"));


        //Настраиваем свойства SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "1025");
        props.put("mail.smtp.auth", "false"); // без авторизации

        //почтовая сессия
        Session session = Session.getInstance(props);
//        session.setDebug(true); // вывод SMTP-команд в консоль

        try {

            MimeMessage message = new MimeMessage(session);

            // От кого
            message.setFrom(new InternetAddress("sender@gmail.com"));

            // Кому
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("receiver@gmail.com", false));

            // Тема письма
            message.setSubject("Statistic from lab 2 application", "UTF-8");

            message.setText(
                    "User statistics received from client:\n\n" +
                            "attempts: " + attempts + "\n" +
                            "hits: " + hits + "\n" +
                            "misses: " + misses + "\n" +
                            "percentage: " + percentage + "%" ,
                    "UTF-8"
            );

            Transport.send(message);

            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("{\"success\": true, \"message\": \"Данные получены\"}");

        } catch (MessagingException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to send email: " + e.getMessage());
        }
    }
}
