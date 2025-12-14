package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.webstore.bo.User;
import org.example.webstore.db.UserDB;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserDB userDB = new UserDB();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDB.getUser(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            if ("admin".equalsIgnoreCase(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/admin/items");
            } else {
                resp.sendRedirect(req.getContextPath() + "/items?categoryId=1");
            }

        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/customer/login.jsp").forward(req, resp);
        }
    }
}



