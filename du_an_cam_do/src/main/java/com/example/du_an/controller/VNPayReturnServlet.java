package com.example.du_an.controller;

import com.example.du_an.config.VNPayConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/vnpay_return")
public class VNPayReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();

        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            String fieldValue = fields.get(fieldName);

            // QUY TẮC VÀNG: Tái tạo chuỗi hash y hệt như lúc gửi đi
            hashData.append(fieldName);
            hashData.append('=');
            hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));

            if (i < fieldNames.size() - 1) {
                hashData.append('&');
            }
        }

        String signValue = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        String responseCode = request.getParameter("vnp_ResponseCode");

        if (signValue.equals(vnp_SecureHash) && "00".equals(responseCode)) {
            request.setAttribute("message", "Thanh toán thành công!");
            // Giải mã URL để hiển thị tiếng Việt đúng trên trang success
            request.setAttribute("orderInfo", URLDecoder.decode(request.getParameter("vnp_OrderInfo"), StandardCharsets.UTF_8.toString()));
            request.setAttribute("paymentAmount", Long.parseLong(request.getParameter("vnp_Amount")) / 100);
            request.setAttribute("transactionNo", request.getParameter("vnp_TransactionNo"));
            request.getRequestDispatcher("views/payment/success.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Thanh toán thất bại do chữ ký không hợp lệ!");
            request.getRequestDispatcher("views/payment/fail.jsp").forward(request, response);
        }
    }
}