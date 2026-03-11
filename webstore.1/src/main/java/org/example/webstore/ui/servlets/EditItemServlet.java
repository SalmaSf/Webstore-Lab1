package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.webstore.bo.Item;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.ui.dto.UserInfoDTO;

import java.io.IOException;

public class EditItemServlet extends HttpServlet {

    private final ItemHandler handler = new ItemHandler();

    private boolean isAdmin(HttpSession session) {
        if (session == null) return false;
        UserInfoDTO user = (UserInfoDTO) session.getAttribute("user");
        return user != null && "admin".equalsIgnoreCase(user.getRole());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (!isAdmin(session)) {
            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
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
        if (!isAdmin(session)) {
            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        handler.updateItem(id, name, description, price, categoryId);
        response.sendRedirect(request.getContextPath() + "/admin/items");
    }
}
