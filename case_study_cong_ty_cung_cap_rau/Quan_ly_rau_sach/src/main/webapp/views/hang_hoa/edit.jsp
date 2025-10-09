<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-3">Cập nhật sản phẩm</h2>

    <form action="/hangHoa?action=edit" method="post">
        <input type="hidden" name="ma_hang" value="${hangHoa.maHangHoa}"/>

        <div class="mb-3">
            <label class="form-label">Tên sản phẩm</label>
            <input type="text" name="ten_hang_hoa" class="form-control" value="${hangHoa.tenHangHoa}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Đơn vị tính</label>
            <input type="text" name="don_vi_tinh" class="form-control" value="${hangHoa.donViTinh}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Giá</label>
            <input type="number" step="0.01" name="don_gia" class="form-control" value="${hangHoa.donGia}" required>
        </div>



        <div class="mb-3">
            <label class="form-label">Danh mục</label>
            <select name="ma_nhom_hang" class="form-select" required>
                <c:forEach var="c" items="${nhomHang}">
                    <option value="${c.maNhomHang}" <c:if test="${c.maNhomHang == hangHoa.maNhomHang}">selected</c:if>>
                            ${c.tenNhomHang}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="/hangHoa" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>