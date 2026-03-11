package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.ui.dto.UserInfoDTO;

import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {

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
        handler.deleteItem(id);

        response.sendRedirect(request.getContextPath() + "/admin/items");
    }
}
