package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.bo.User;
import org.example.webstore.ui.dto.ItemInfoDTO;

import java.io.IOException;
import java.util.List;

public class AdminItemServlet extends HttpServlet {

    private final ItemHandler handler = new ItemHandler();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Access control
        if (session == null ||
                session.getAttribute("user") == null ||
                !((User) session.getAttribute("user")).isAdmin()) {

            response.sendRedirect("/webstore/customer/login.jsp");
            return;
        }

        List<ItemInfoDTO> items = handler.getItems();
        request.setAttribute("items", items);

        request.getRequestDispatcher("/admin/adminItems.jsp").forward(request, response);
    }
}
