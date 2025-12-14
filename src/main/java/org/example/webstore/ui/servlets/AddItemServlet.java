package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.bo.User;
import java.io.IOException;

public class AddItemServlet extends HttpServlet {

    private final ItemHandler handler = new ItemHandler();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !((User) session.getAttribute("user")).isAdmin()) {

            response.sendRedirect("/webstore/customer/login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        handler.addItem(name, description, price, categoryId);
        response.sendRedirect("/webstore/admin/items");
    }
}
