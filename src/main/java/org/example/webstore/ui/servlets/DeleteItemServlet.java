package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.bo.User;

import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {

    private final ItemHandler handler = new ItemHandler();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null ||
                !((User) session.getAttribute("user")).isAdmin()) {
            response.sendRedirect("/webstore/customer/login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        handler.deleteItem(id);
        response.sendRedirect("/webstore/admin/items");
    }
}
