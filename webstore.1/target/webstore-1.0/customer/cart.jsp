<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>

<h2>Your Shopping Cart</h2>

<%
    org.example.webstore.bo.ShoppingCart cart =
            (org.example.webstore.bo.ShoppingCart) session.getAttribute("cart");
%>

<% if (cart == null || cart.getItems().isEmpty()) { %>
<p>Your cart is empty.</p>
<% } else { %>

<table border="1" cellpadding="10">
    <tr>
        <th>Item</th>
        <th>Qty</th>
        <th>Total</th>
    </tr>

    <% for (org.example.webstore.bo.CartItem item : cart.getItems()) { %>
    <tr>
        <td><%= item.getItem().getName() %></td>
        <td><%= item.getQuantity() %></td>
        <td><%= item.getTotalPrice() %> €</td>
    </tr>
    <% } %>

</table>

<p><strong>Total:</strong> <%= cart.getTotalPrice() %> €</p>

<% } %>

<a href="/webstore/items?categoryId=1">Continue shopping</a>

</body>
</html>
