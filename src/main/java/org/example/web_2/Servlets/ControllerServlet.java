package org.example.web_2.Servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.web_2.Result;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

            response.sendRedirect(request.getContextPath() + "/result");


        } catch (NumberFormatException e) {
            response.setStatus(400);
            request.setAttribute("error", "Неверный формат чисел");
            request.getRequestDispatcher("pages/index.jsp").forward(request, response);
        }

    }
}
