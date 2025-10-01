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
<a href="/customers?action=add"> Thêm mới </a>
<h1>Danh sách khách hàng</h1>
<table class="table table-dark table-striped">
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Email</th>
    </tr>
    <c:forEach var="customer" items="${customerList}" varStatus="status">
        <tr>

            <td>${customer.name}</td>
            <td>${customer.dateOfBirth}</td>
            <td>${customer.address}</td>
            <td>${customer.email}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>