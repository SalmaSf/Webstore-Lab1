<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.webstore.bo.User" %>
<%@ page import="org.example.webstore.ui.dto.ItemInfoDTO" %>
<html>
<head>
    <title>Admin Product Management</title>
</head>
<body>

<%
    User logged = (User) session.getAttribute("user");
    if (logged == null || !logged.isAdmin()) {
        response.sendRedirect("/webstore/customer/login.jsp");
        return;
    }
%>

<h2>Manage Products</h2>

<button type="button" onclick="window.location.href='/webstore/items?categoryId=1'">
    Go Back to Webshop
</button>
<br><br>


<a href="/webstore/admin/addItem.jsp">Add New Product</a><br><br>

<table border="1" cellpadding="8">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>

    <%
        java.util.List<ItemInfoDTO> items =
                (java.util.List<ItemInfoDTO>) request.getAttribute("items");

        if (items != null) {
            for (ItemInfoDTO item : items) {
    %>
    <tr>
        <td><%= item.getName() %></td>
        <td><%= item.getDescription() %></td>
        <td><%= item.getPrice() %> €</td>
        <td>
            <a href="/webstore/admin/editItem?id=<%=item.getId()%>">Edit</a> |
            <a href="/webstore/admin/deleteItem?id=<%=item.getId()%>">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>

</body>
</html>
