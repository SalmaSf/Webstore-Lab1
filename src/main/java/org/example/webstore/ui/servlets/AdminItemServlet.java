package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.ui.dto.ItemInfoDTO;
import org.example.webstore.ui.dto.UserInfoDTO;

import java.io.IOException;
import java.util.List;

public class AdminItemServlet extends HttpServlet {

    private final ItemHandler handler = new ItemHandler();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Access control: must be logged in AND admin
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
            return;
        }

        UserInfoDTO user = (UserInfoDTO) session.getAttribute("user");
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
            return;
        }

        List<ItemInfoDTO> items = handler.getItems();
        request.setAttribute("items", items);

        request.getRequestDispatcher("/admin/adminItems.jsp").forward(request, response);
    }
}
