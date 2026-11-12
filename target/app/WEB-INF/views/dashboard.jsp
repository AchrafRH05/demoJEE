
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Mini Site MVC</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f4f6;
            margin: 0;
            padding: 40px;
        }

        .container {
            max-width: 700px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #ddd;
        }

        a {
            display: inline-block;
            padding: 12px 20px;
            margin: 10px 5px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
        }

        .products {
            background: #2563eb;
        }

        .logout {
            background: #dc2626;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Dashboard</h1>

    <h2>
        Welcome, ${requestScope.user}!
    </h2>

    <p>You are successfully logged in.</p>

    <a class="products"
       href="${pageContext.request.contextPath}/produits">
        Manage Products
    </a>

    <a class="logout"
       href="${pageContext.request.contextPath}/logout">
        Logout
    </a>

</div>

</body>
</html>
