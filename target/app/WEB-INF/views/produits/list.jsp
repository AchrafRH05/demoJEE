
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f4f6;
            padding: 30px;
        }

        .container {
            max-width: 1000px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #2563eb;
            color: white;
        }

        .btn {
            display: inline-block;
            padding: 8px 12px;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .add {
            background: #16a34a;
        }

        .edit {
            background: #2563eb;
        }

        .delete {
            background: #dc2626;
        }

        .out-of-stock {
            color: red;
            font-weight: bold;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Product Management</h1>

    <p>
        <a class="btn add"
           href="${pageContext.request.contextPath}/produits?action=new">
            + Add Product
        </a>

        <a class="btn edit"
           href="${pageContext.request.contextPath}/dashboard">
            Dashboard
        </a>
    </p>

    <c:if test="${not empty sessionScope.message}">
        <p style="color: green;">
            <c:out value="${sessionScope.message}"/>
        </p>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach var="produit" items="${requestScope.produits}">

                <tr>
                    <td><c:out value="${produit.id}"/></td>

                    <td><c:out value="${produit.nom}"/></td>

                    <td><c:out value="${produit.description}"/></td>

                    <td>
                        <fmt:formatNumber value="${produit.prix}"
                                          minFractionDigits="2"
                                          maxFractionDigits="2"/>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${produit.stock == 0}">
                                <span class="out-of-stock">Out of stock</span>
                            </c:when>

                            <c:otherwise>
                                <c:out value="${produit.stock}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <a class="btn edit"
                           href="${pageContext.request.contextPath}/produits?action=edit&id=${produit.id}">
                            Edit
                        </a>

                        <form action="${pageContext.request.contextPath}/produits"
                              method="post"
                              style="display:inline;"
                              onsubmit="return confirm('Are you sure you want to delete this product?');">

                            <input type="hidden" name="action" value="delete">

                            <input type="hidden"
                                   name="id"
                                   value="${produit.id}">

                            <button type="submit" class="btn delete">
                                Delete
                            </button>
                        </form>
                    </td>
                </tr>

            </c:forEach>

        </tbody>
    </table>

    <c:if test="${empty requestScope.produits}">
        <p>No products found.</p>
    </c:if>

</div>

</body>
</html>
