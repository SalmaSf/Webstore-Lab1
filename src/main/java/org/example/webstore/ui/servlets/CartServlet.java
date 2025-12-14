package org.example.webstore.ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.webstore.bo.ShoppingCart;
import java.io.IOException;

public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        request.setAttribute("cart", cart);

        request.getRequestDispatcher("/customer/cart.jsp").forward(request, response);
    }
}
