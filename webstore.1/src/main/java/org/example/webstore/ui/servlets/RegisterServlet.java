package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.UserHandler;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    private final UserHandler handler = new UserHandler();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            handler.registerUser(username, password);
            request.setAttribute("success", "Account created! Please log in.");
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/customer/register.jsp").forward(request, response);
    }
}
