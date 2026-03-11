package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.webstore.bo.User;
import org.example.webstore.bo.UserHandler;
import org.example.webstore.ui.dto.UserInfoDTO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    private final UserHandler userHandler = new UserHandler();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userHandler.authenticateUser(username, password);

            if (user != null) {
                HttpSession session = req.getSession();

                UserInfoDTO userDTO = new UserInfoDTO(user.getId(), user.getUsername(), user.getRole());
                session.setAttribute("user", userDTO);

                if ("admin".equalsIgnoreCase(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin/items");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/items?categoryId=1");
                }
                return;
            }

            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/customer/login.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
