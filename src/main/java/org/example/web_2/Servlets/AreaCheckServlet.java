package org.example.web_2.Servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.web_2.HitChecker;
import org.example.web_2.Result;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/result")
public class AreaCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Boolean processed = (Boolean) session.getAttribute("requestProcessed");

        String xFromRequest = (String) session.getAttribute("x");
        String yFromRequest = (String) session.getAttribute("y");
        String rFromRequest = (String) session.getAttribute("r");
        Long processingTimeFromRequest = (Long) session.getAttribute("processingTime");

        BigDecimal x = BigDecimal.valueOf(Double.parseDouble(xFromRequest));
        BigDecimal y = BigDecimal.valueOf(Double.parseDouble(yFromRequest));
        BigDecimal r = BigDecimal.valueOf(Double.parseDouble(rFromRequest));

        HitChecker hitChecker = new HitChecker(x, y, r);
        String hit = hitChecker.check() ? "ДА" : "НЕТ";

        String localTime;
        String processingTime;

        if (processed == null || !processed) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            localTime = now.format(formatter);

            long processingMs = (System.nanoTime() - processingTimeFromRequest) / 1_000_000;
            processingTime = Long.toString(processingMs);

            session.setAttribute("localTime", localTime);
            session.setAttribute("processingTime", Long.valueOf(processingTime));

            session.setAttribute("requestProcessed", true);

        } else {

            localTime = (String) session.getAttribute("localTime");
            Long processingTimeFromSession = (Long) session.getAttribute("processingTime");
            processingTime = Long.toString(processingTimeFromSession);

        }

        Result result = new Result(x, y, r, hit, localTime, processingTime);


        ServletContext context = getServletContext();
        List<Result> results = (List<Result>) context.getAttribute("results");

        if (results == null) {
            results = new ArrayList<>();
        }

        if (processed == null || !processed) {
            results.add(result);
        }
        context.setAttribute("results", results);

        request.setAttribute("result", result);

        request.getRequestDispatcher("pages/result.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/main");
    }
}
