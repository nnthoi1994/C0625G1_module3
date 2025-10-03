<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Thêm sản phẩm mới</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
<h2>Thêm sản phẩm mới</h2>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<form action="/products?action=add" method="post">
    <p>
        <label>Tên sản phẩm:</label><br>
        <input type="text" name="name" required>
    </p>

    <p>
        <label>Giá sản phẩm:</label><br>
        <input type="number" name="price" min="0" required>
    </p>

    <p>
        <label>Danh mục:</label><br>
        <select name="category_id" required>
            <option value="">-- Chọn danh mục --</option>
            <c:forEach items="${categoryList}" var="cate">
                <option value="${cate.id}">${cate.name}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <button type="submit">Lưu</button>
        <a href="/products">Quay lại</a>
    </p>
</form>
</body>
</html>
