<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>Create Account</h2>

<form action="/webstore/register" method="post">
    Username: <input type="text" name="username" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    <button type="submit">Register</button>
</form>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red"><%= request.getAttribute("error") %></p>
<% } %>

<% if (request.getAttribute("success") != null) { %>
<p style="color:green"><%= request.getAttribute("success") %></p>
<a href="/webstore/customer/login.jsp"
   style="font-size: 18px; color: blue; text-decoration: underline;">
    Go to Login
</a>

<!-- Hide form after success -->
<style>
    form {
        display: none;
    }
</style>
<% } %>

</body>
</html>
