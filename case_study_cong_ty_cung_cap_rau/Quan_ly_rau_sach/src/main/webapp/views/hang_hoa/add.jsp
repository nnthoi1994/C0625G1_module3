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
<form action="/hangHoa?action=add" method="post">
    <p>
        <label>Tên sản phẩm:</label><br>
        <input type="text" name="ten_hang_hoa" required>
    </p>

    <p>
        <label>Đơn vị tính:</label><br>
        <input type="text" name="don_vi_tinh"  required>
    </p>

    <p>
        <label>Giá sản phẩm:</label><br>
        <input type="number" name="don_gia" min="0" required>
    </p>

    <p>
        <label>Danh mục:</label><br>
        <select name="ma_loai_hang" required>
            <option value="">-- Chọn danh mục --</option>
            <c:forEach items="${nhomHang}" var="cate">
                <option value="${cate.maNhomHang}">${cate.tenNhomHang}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <button type="submit">Lưu</button>
        <a href="/hangHoa">Quay lại</a>
    </p>
</form>
</body>
</html>
