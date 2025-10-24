package com.example.du_an.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ChatAIController", value = "/chat-ai")
public class ChatAIController extends HttpServlet {

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String conversationHistoryJson = sb.toString();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);

            // THÊM HEADER MỚI THEO YÊU CẦU TỪ cURL
            conn.setRequestProperty("Groq-Model-Version", "latest");

            conn.setDoOutput(true);

            // SỬ DỤNG MODEL MỚI: "groq/compound"
            // Tôi đã bỏ qua phần "compound_custom" vì nó dành cho các công cụ nâng cao
            // và không cần thiết cho một cuộc trò chuyện văn bản thông thường.
            String jsonInputString = "{"
                    + "\"messages\": " + conversationHistoryJson + ","
                    + "\"model\": \"groq/compound\"," // <-- ĐÃ THAY ĐỔI MODEL
                    + "\"temperature\": 0.7,"
                    + "\"max_tokens\": 1024,"
                    + "\"stream\": false"
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            StringBuilder responseBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    responseBuilder.append(responseLine.trim());
                }
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(responseBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Lỗi khi kết nối đến AI service.\"}");
        }
    }
}