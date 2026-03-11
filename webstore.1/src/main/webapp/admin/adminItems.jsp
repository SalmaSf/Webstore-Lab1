<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<%@ page import="java.util.List" %>
<%@ page import="org.example.webstore.ui.dto.UserInfoDTO" %>
<%@ page import="org.example.webstore.ui.dto.ItemInfoDTO" %>

<%
    UserInfoDTO logged = (UserInfoDTO) session.getAttribute("user");
    if (logged == null || !logged.isAdmin()) {
        response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
        return;
    }

    List<ItemInfoDTO> items = (List<ItemInfoDTO>) request.getAttribute("items");
%>

<html>
<head>
    <title>Admin Product Management</title>
</head>
<body>

<h2>Manage Products</h2>

<button type="button" onclick="window.location.href='<%=request.getContextPath()%>/items?categoryId=1'">
    Go Back to Webshop
</button>
<br><br>

<a href="<%=request.getContextPath()%>/admin/addItem.jsp">Add New Product</a><br><br>

<table border="1" cellpadding="8">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>

    <%
        if (items != null) {
            for (ItemInfoDTO item : items) {
    %>
    <tr>
        <td><%= item.getName() %></td>
        <td><%= item.getDescription() %></td>
        <td><%= item.getPrice() %> €</td>
        <td>
            <a href="<%=request.getContextPath()%>/admin/editItem?id=<%= item.getId() %>">Edit</a> |
            <a href="<%=request.getContextPath()%>/admin/deleteItem?id=<%= item.getId() %>">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>

</body>
</html>
