
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Form</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f4f6;
            padding: 30px;
        }

        .container {
            max-width: 500px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 8px 0 15px;
            box-sizing: border-box;
        }

        button, .back {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            color: white;
            cursor: pointer;
            text-decoration: none;
        }

        button {
            background: #16a34a;
        }

        .back {
            background: #2563eb;
        }

        .error {
            color: red;
            font-size: 14px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>
        <c:choose>
            <c:when test="${produit.id != null}">
                Edit Product
            </c:when>
            <c:otherwise>
                Add Product
            </c:otherwise>
        </c:choose>
    </h2>

    <form action="${pageContext.request.contextPath}/produits"
          method="post">

        <!-- Product ID: only used when editing -->
        <c:if test="${produit.id != null}">
            <input type="hidden"
                   name="id"
                   value="${produit.id}">
        </c:if>

        <!-- Product name -->
        <label>Product Name</label>

        <input type="text"
               name="nom"
               value="<c:out value='${produit.nom}'/>"
               minlength="2"
               maxlength="50"
               required>

        <c:if test="${not empty erreurs.nom}">
            <p class="error">
                <c:out value="${erreurs.nom}"/>
            </p>
        </c:if>

        <!-- Description -->
        <label>Description</label>

        <textarea name="description"
                  maxlength="255"
                  rows="4"><c:out value="${produit.description}"/></textarea>

        <c:if test="${not empty erreurs.description}">
            <p class="error">
                <c:out value="${erreurs.description}"/>
            </p>
        </c:if>

        <!-- Price -->
        <label>Price</label>

        <input type="number"
               name="prix"
               step="0.01"
               min="0.01"
               value="${produit.prix}"
               required>

        <c:if test="${not empty erreurs.prix}">
            <p class="error">
                <c:out value="${erreurs.prix}"/>
            </p>
        </c:if>

        <!-- Stock -->
        <label>Stock</label>

        <input type="number"
               name="stock"
               min="0"
               step="1"
               value="${produit.stock}">

        <c:if test="${not empty erreurs.stock}">
            <p class="error">
                <c:out value="${erreurs.stock}"/>
            </p>
        </c:if>

        <br>

        <button type="submit">Save Product</button>

        <a class="back"
           href="${pageContext.request.contextPath}/produits">
            Cancel
        </a>

    </form>

</div>

</body>
</html>
