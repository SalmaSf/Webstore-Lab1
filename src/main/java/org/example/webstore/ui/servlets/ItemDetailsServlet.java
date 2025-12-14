package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.ui.dto.ItemInfoDTO;
import java.io.IOException;

public class ItemDetailsServlet extends HttpServlet {

    private final ItemHandler handler = new ItemHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int itemId = Integer.parseInt(req.getParameter("id"));
        ItemInfoDTO item = handler.getItemDetails(itemId);

        req.setAttribute("item", item);
        req.getRequestDispatcher("/customer/itemDetails.jsp").forward(req, resp);
    }
}
