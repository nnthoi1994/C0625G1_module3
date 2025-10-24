<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Thêm phòng trọ mới</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
<h2>Thêm phòng trọ mới</h2>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<form action="/phongTro?action=add" method="post">
    <p>
        <label>Tên phòng trọ:</label><br>
        <input type="text" name="ten_nguoi_thue_tro" required>
    </p>

    <p>
        <label>Số điện thoại:</label><br>
        <input type="text" name="so_dien_thoai"  required>
    </p>

    <p>
        <label>Ngày bắt đầu:</label><br>
        <input type="text" name="ngay_bat_dau"  required>
    </p>





    <p>
        <label>Hình thức thanh toán:</label><br>
        <select name="ma_thanh_toan" required>
            <option value="">-- Chọn hình thức thanh toán --</option>
            <c:forEach items="${thanhToan}" var="cate">
                <option value="${cate.maThanhToan}">${cate.tenThanhToan}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <label>Ghi chú:</label><br>
        <input type="text" name="ghi_chu"  required>
    </p>

    <p>
        <button type="submit">Lưu</button>
        <a href="/phongTro">Quay lại</a>
    </p>
</form>
</body>
</html>
