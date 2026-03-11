<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.webstore.bo.User" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>

<%
    User logged = (User) session.getAttribute("user");
    if (logged == null || !logged.isAdmin()) {
        response.sendRedirect("/webstore/customer/login.jsp");
        return;
    }
%>

<h2>Add New Product</h2>

<form action="/webstore/admin/addItem" method="post">
    Name: <input type="text" name="name" required><br><br>

    Description:<br>
    <textarea name="description" rows="3" cols="30" required></textarea><br><br>

    Price: <input type="number" step="0.01" name="price" required><br><br>

    Category:
    <select name="categoryId">
        <option value="1">Electronics</option>
        <option value="2">Clothes</option>
        <option value="3">Books</option>
    </select>
    <br><br>

    <button type="submit">Add Product</button>
</form>

<br>
<a href="/webstore/admin/items">Back to Admin Panel</a>

</body>
</html>
