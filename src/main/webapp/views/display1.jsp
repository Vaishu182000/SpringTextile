<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Products</title>
</head>
<body>
<table width: 100%; text-align:center>
    <tr>
        <th>ProductId</th>
        <th>ProductName</th>
        <th>ProductPrice</th>
        <th>ProductImage</th>
        <th>Quantity_available</th>
    </tr>
    <tbody>
        <c:forEach items="${products}" var="product">
        <tr>
                <td><c:out value="${product.productid}" /></td>
                <td><c:out value="${product.productname}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><img src="data:image/jpg;base64,${product.image}" width=220px; height=220px/></td>
                <td><c:out value="${product.quantity_aval}"/></td>
        </tr>   
        </c:forEach>
        </tbody>
</table>
<table width: 100%; text-align:center>
    <tr>
        <th>ProductId</th>
        <th>ProductName</th>
        <th>ProductPrice</th>
        <th>ProductImage</th>
        <th>Quantity_available</th>
    </tr>
    <tbody>
        <c:forEach items="${products}" var="product">
        <tr>
            <form action="${pageContext.request.contextPath }/addcart/${product.productid}" method="get">
            <td><c:out value="${product.productid}" /></td>
                <td><c:out value="${product.productname}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><img src="data:image/jpg;base64,${product.image}" width=220px; height=220px/></td>
                <td><c:out value="${product.quantity_aval}"/></td>
                <td>Quantity..
                    <input type="number" min="0" max="${product.quantity_aval}" name="quantity"/></td>
                 <input type="submit" value="Add to cart">
                </form>
        </tr>  
        </c:forEach>
        </tbody>
</table>
</body>
</html>