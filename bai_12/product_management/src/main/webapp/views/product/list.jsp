<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 9/26/2025
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
<c:import url="../layout/navbar.jsp"/>
<a href="/products?action=add"> Thêm mới </a>
<h1>Danh sách sản phẩm</h1>
<table class="table table-dark table-striped">
    <tr>
        <th>Tên</th>
        <th>Giá</th>
        <th>Tên nhóm</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach var="product" items="${productList}" varStatus="status">
        <tr>

            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.category}</td>
            <td>
                <a href="/products?action=edit&id=${product.id}" class="btn btn-warning btn-sm">Sửa</a>
                <a href="/products?action=delete&id=${product.id}"
                   onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?');"
                   class="btn btn-danger btn-sm">Xóa</a>
            </td>

        </tr>
    </c:forEach>
</table>
</body>
</html>