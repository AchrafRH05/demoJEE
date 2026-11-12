
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Mini Site MVC</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-box {
            background: white;
            padding: 30px;
            border-radius: 10px;
            width: 320px;
            box-shadow: 0 0 10px #ccc;
        }

        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;
            background: #2563eb;
            color: white;
            border: none;
            cursor: pointer;
        }

        .error {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<div class="login-box">

    <h2>Login</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <label>Username</label>
        <input type="text"
               name="username"
               value="${requestScope.username}"
               required>

        <label>Password</label>
        <input type="password"
               name="password"
               required>

        <button type="submit">Login</button>

    </form>

    <p>Username: admin</p>
    <p>Password: password</p>

</div>

</body>
</html>
