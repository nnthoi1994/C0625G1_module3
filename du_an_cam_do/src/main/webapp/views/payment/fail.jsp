<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanh toán thất bại</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-light">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center mt-5">
            <div class="card shadow">
                <div class="card-body py-5">
                    <i class="fas fa-times-circle fa-5x text-danger mb-4"></i>
                    <h1 class="card-title">Thanh toán thất bại!</h1>
                    <p class="text-muted">Giao dịch của bạn đã không thể hoàn tất. Vui lòng thử lại.</p>
                    <a href="${pageContext.request.contextPath}/liquidation-products" class="btn btn-secondary mt-3">Quay lại trang sản phẩm</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>