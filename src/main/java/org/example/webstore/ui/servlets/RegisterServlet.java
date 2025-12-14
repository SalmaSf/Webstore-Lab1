package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.UserHandler;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private final UserHandler handler = new UserHandler();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean created = handler.registerUser(username, password);

        if (created) {
            request.setAttribute("success", "Account created! Please log in.");
        } else {
            request.setAttribute("error", "Username already exists!");
        }

        request.getRequestDispatcher("/customer/register.jsp").forward(request, response);
    }
}
