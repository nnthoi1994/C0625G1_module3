<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 25/09/2025
  Time: 2:24 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ứng dụng tính chiết khấu</title>
</head>
<body>
<form action="/sum" method="post">

    <h2>Tính chiết khấu</h2><br/>
    <label>Mô tả sản phẩm:</label>
    <input name="ten" value="${ten}"><br/>

    <label>Giá sản phẩm:</label>
    <input name="n1" value="${num1}"><br/>
    <label>Tỉ lệ chiết khấu:</label>
    <input name="n2" value="${num2}"><br/>
    <button>Tính chiết khấu</button>
</form>
<h3>Giá sau chiết khấu: ${calculator}</h3>
</body>
</html>