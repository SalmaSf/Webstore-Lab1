package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.ui.dto.ItemInfoDTO;
import java.io.IOException;
import java.util.List;

//@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private final ItemHandler itemHandler = new ItemHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String categoryIdParam = req.getParameter("categoryId");
        int categoryId = (categoryIdParam != null) ? Integer.parseInt(categoryIdParam) : 1;

        List<ItemInfoDTO> items = itemHandler.getItemsByCategory(categoryId);

        req.setAttribute("items", items);
        req.getRequestDispatcher("/customer/items.jsp").forward(req, resp);
    }
}
