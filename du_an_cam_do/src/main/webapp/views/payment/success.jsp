<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Thanh toán thành công</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-light">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center mt-5">
            <div class="card shadow">
                <div class="card-body py-5">
                    <i class="fas fa-check-circle fa-5x text-success mb-4"></i>
                    <h1 class="card-title">Thanh toán thành công!</h1>
                    <p class="text-muted">Cảm ơn bạn đã mua hàng.</p>
                    <ul class="list-group list-group-flush my-4">
                        <li class="list-group-item"><strong>Nội dung:</strong> ${orderInfo}</li>
                        <li class="list-group-item"><strong>Số tiền:</strong>
                            <fmt:formatNumber value="${paymentAmount}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                        </li>
                        <li class="list-group-item"><strong>Mã giao dịch VNPay:</strong> ${transactionNo}</li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/liquidation-products" class="btn btn-primary">Tiếp tục mua sắm</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>