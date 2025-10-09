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
    <title>Quản lý sản phảm rau củ quả</title>
    <link rel="stylesheet" href="bootstrap520/css/bootstrap.min.css" />
    <link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css" />
    <c:import url="../layout/library.jsp"/>
    <c:import url="../layout/navbar.jsp"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-3">Danh sách sản phẩm</h2>
    <form class="row mb-3" method="get" action="/hangHoa">
        <div class="col">
            <input type="text" name="tenHang" value="${tenHang}" class="form-control" placeholder="Tìm theo tên">
        </div>
        <div class="col">
            <select name="maLoaiHang" class="form-select">
                <option value="">Tất cả danh mục</option>
                <c:forEach var="n" items="${nhomHang}">
                    <option value="${n.maNhomHang}"
                            <c:if test="${n.maNhomHang == selectedNhomHang}">selected</c:if>>
                            ${n.tenNhomHang}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            <a href="/hangHoa" class="btn btn-secondary">Reset</a>
        </div>
        <div class="col">
            <a href="/hangHoa?action=add" class="btn btn-success">Thêm mới sản phẩm</a>
        </div>
    </form>

    <h1>Danh sách sản phẩm</h1>
    <table id="tableProduct" class="table table-dark table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên</th>
            <th>Đơn vị tính</th>
            <th>Giá</th>
            <th>Tên nhóm</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${sanPhamList}" varStatus="status">
            <tr>
                <td>${startIndex + status.index + 1}</td>
                <td>${product.tenHangHoa}</td>
                <td>${product.donViTinh}</td>
                <td>${product.donGia}</td>
                <td>${product.tenNhomHang}</td>
                <td>
                    <a href="/hangHoa?action=edit&id=${product.maHangHoa}" class="btn btn-warning btn-sm">Sửa</a>
                    <!-- Nút mở modal -->
                    <a href="#" class="btn btn-danger btn-sm"
                       data-bs-toggle="modal"
                       data-bs-target="#confirmDeleteModal"
                       data-id="${product.maHangHoa}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal xác nhận xóa -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title">Xác nhận xóa</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn xóa sản phẩm này không?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Xóa</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var deleteModal = document.getElementById("confirmDeleteModal");
        var confirmDeleteBtn = document.getElementById("confirmDeleteBtn");

        deleteModal.addEventListener("show.bs.modal", function (event) {
            var button = event.relatedTarget;   // nút Xóa được click
            var productId = button.getAttribute("data-id");
            confirmDeleteBtn.href = "/products?action=delete&id=" + productId;
        });
    });
</script>


<script src="jquery/jquery-3.5.1.min.js"></script>
<script src="datatables/js/jquery.dataTables.min.js"></script>
<script src="datatables/js/dataTables.bootstrap5.min.js"></script>
<script>
    $(document).ready(function() {
        $('#tableProduct').dataTable( {
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        } );
    } );
</script>

</body>
</html>
