<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product Details</title>
</head>
<body>

<%
    org.example.webstore.ui.dto.ItemInfoDTO item =
            (org.example.webstore.ui.dto.ItemInfoDTO) request.getAttribute("item");
%>

<h2><%= item.getName() %></h2>
<p><strong>Description:</strong> <%= item.getDescription() %></p>
<p><strong>Price:</strong> <%= item.getPrice() %> €</p>

<a href="/webstore/items?categoryId=<%= item.getId() %>">Back to list</a>
<br><br>

<form action="/webstore/addToCart" method="post">
    <input type="hidden" name="itemId" value="<%= item.getId() %>">
    <button type="submit">Add to Cart</button>
</form>

</body>
</html>
