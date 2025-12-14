package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.bo.User;
import org.example.webstore.bo.Item;

import java.io.IOException;

public class EditItemServlet extends HttpServlet {

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
        Item item = handler.getItemBusinessObject(id);

        request.setAttribute("item", item);
        request.getRequestDispatcher("/admin/editItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !((User) session.getAttribute("user")).isAdmin()) {
            response.sendRedirect("/webstore/customer/login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        handler.updateItem(id, name, description, price, categoryId);
        response.sendRedirect("/webstore/admin/items");
    }
}
