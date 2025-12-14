<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Products</title>
    <style>
        body { font-family: Arial; }
        .sidebar {
            width: 200px;
            float: left;
            border-right: 1px solid #000;
            padding-right: 20px;
        }
        .content {
            margin-left: 230px;
        }
        .category {
            margin: 10px 0;
        }
        .category a {
            text-decoration: none;
            font-size: 18px;
            color: black;
        }
        .category a:hover {
            font-weight: bold;
        }
        table {
            border-collapse: collapse;
            width: 60%;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }
        h2 {
            margin-top: 0;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h3>Categories</h3>
    <div class="category"><a href="/webstore/items?categoryId=1">Electronics</a></div>
    <div class="category"><a href="/webstore/items?categoryId=2">Clothes</a></div>
    <div class="category"><a href="/webstore/items?categoryId=3">Books</a></div>

    <hr>

    <h3>Shopping</h3>
    <div class="category">
        <a href="/webstore/cart">
            View Cart (<%= session.getAttribute("cart") != null
                ? ((org.example.webstore.bo.ShoppingCart) session.getAttribute("cart")).getItemCount()
                : 0 %>)
        </a>
    </div>



    <hr>
    <h3>Account</h3>

    <%
        org.example.webstore.bo.User loggedUser =
                (org.example.webstore.bo.User) session.getAttribute("user");
    %>

    <% if (loggedUser == null) { %>

    <div class="category"><a href="/webstore/customer/login.jsp">Login</a></div>
    <div class="category"><a href="/webstore/customer/register.jsp">Register</a></div>

    <% } else { %>

    <div class="category"><a href="/webstore/logout">Logout</a></div>

    <% if (loggedUser.isAdmin()) { %>
    <hr>
    <h3>Admin</h3>
    <div class="category"><a href="/webstore/admin/items">Manage Products</a></div>
    <% } %>

    <% } %>


</div>


<div class="content">
    <h2>Product List</h2>

    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>

        <%
            java.util.List<org.example.webstore.ui.dto.ItemInfoDTO> items =
                    (java.util.List<org.example.webstore.ui.dto.ItemInfoDTO>) request.getAttribute("items");

            if (items != null) {
                for (org.example.webstore.ui.dto.ItemInfoDTO item : items) {
        %>
        <tr>
            <td>
                <a href="/webstore/itemDetails?id=<%= item.getId() %>">
                    <%= item.getName() %>
                </a>
            </td>

            <td><%= item.getDescription() %></td>
            <td><%= item.getPrice() %> €</td>
        </tr>
        <%
                }
            }
        %>

    </table>
</div>

</body>
</html>
