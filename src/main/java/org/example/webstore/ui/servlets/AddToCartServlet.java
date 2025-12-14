package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.ItemHandler;
import org.example.webstore.bo.ShoppingCart;
import org.example.webstore.bo.Item;
import java.io.IOException;

public class AddToCartServlet extends HttpServlet {

    private final ItemHandler handler = new ItemHandler();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Item item = handler.getItemBusinessObject(itemId);

        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        cart.addItem(item);
        response.sendRedirect("/webstore/cart");
    }
}

