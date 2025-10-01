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
    <title>Thêm mới</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
<c:import url="../layout/navbar.jsp"/>
<h1>Thêm mới</h1>
<form action="/customers?action=add" method="post">
    Tên khách hàng
    <input name="name"><br>
    Ngày sinh
    <input name="dateOfBirth"><br>
    Địa chỉ
    <input name="address"><br>
    Email
    <input name="email"><br>
    <button>Lưu</button>
</form>
</body>
</html>