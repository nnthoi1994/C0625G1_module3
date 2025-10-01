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
<form action="/products?action=add" method="post">
    Tên sản phẩm
    <input name="name"><br>

    Giá sản phẩm
    <input name="price"><br>

    Mã nhóm
    <select name="category_id">
        <c:forEach items="${categoryList}" var="cate">
            <option value="${cate.id}">${cate.name}</option>
        </c:forEach>
    </select>

    <button>Thêm mới</button>
</form>
</body>
</html>