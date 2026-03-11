<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.webstore.bo.User" %>
<%@ page import="org.example.webstore.bo.Item" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>

<%
    User logged = (User) session.getAttribute("user");
    if (logged == null || !logged.isAdmin()) {
        response.sendRedirect("/webstore/customer/login.jsp");
        return;
    }

    Item item = (Item) request.getAttribute("item");
%>

<h2>Edit Product</h2>

<form action="/webstore/admin/editItem" method="post">

    <input type="hidden" name="id" value="<%= item.getId() %>">

    Name: <input type="text" name="name" value="<%= item.getName() %>" required><br><br>

    Description:<br>
    <textarea name="description" rows="3" cols="30" required><%= item.getDescription() %></textarea><br><br>

    Price: <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>" required><br><br>

    Category:
    <select name="categoryId">
        <option value="1" <%= item.getCategoryId() == 1 ? "selected" : "" %>>Electronics</option>
        <option value="2" <%= item.getCategoryId() == 2 ? "selected" : "" %>>Clothes</option>
        <option value="3" <%= item.getCategoryId() == 3 ? "selected" : "" %>>Books</option>
    </select>
    <br><br>

    <button type="submit">Save Changes</button>
</form>

<br>
<a href="/webstore/admin/items">Cancel</a>

</body>
</html>
