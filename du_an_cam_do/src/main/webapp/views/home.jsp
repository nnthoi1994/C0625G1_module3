<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cầm Đồ Nhanh | Giải Pháp Tài Chính Tin Cậy</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap');

        body {
            font-family: 'Inter', sans-serif;
            background-color: #f7f9fb;
        }

        .text-primary-color { color: #DC2626; }
        .bg-primary-color { background-color: #DC2626 !important; }

        .hero-bg {
            background-image: url('<%= request.getContextPath() %>/img/bg.png');
            background-size: cover;
            background-position: center;
            min-height: 500px;
            position: relative;
        }

        .hero-overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.6);
        }

        .service-img {
            height: 180px;
            object-fit: cover;
            width: 100%;
            padding: 1rem;
        }

        .card:hover {
            transform: translateY(-3px);
            transition: all 0.3s ease;
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
        }

        .hover-shadow:hover {
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
        }

        .text-sm { font-size: .95rem; }
        .z-1 { z-index: 1; }

        /* ===== CSS MỚI CHO CHATBOX ===== */
        .chat-bubble {
            position: fixed;
            bottom: 30px;
            right: 30px;
            width: 60px;
            height: 60px;
            background-color: #DC2626;
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 28px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            z-index: 1000;
            transition: transform 0.2s;
        }

        .chat-bubble:hover {
            transform: scale(1.1);
        }

        .chatbox {
            position: fixed;
            bottom: 100px;
            right: 30px;
            width: 350px;
            height: 500px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            display: flex;
            flex-direction: column;
            overflow: hidden;
            z-index: 1000;
            transform: scale(0);
            transform-origin: bottom right;
            transition: transform 0.3s ease-in-out;
        }

        .chatbox.active {
            transform: scale(1);
        }

        .chatbox-header {
            background: #DC2626;
            color: white;
            padding: 15px;
            font-weight: bold;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .chat-clear-btn {
            background: none;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
            opacity: 0.8;
        }

        .chat-clear-btn:hover {
            opacity: 1;
        }

        .chatbox-close {
            cursor: pointer;
            font-size: 20px;
        }

        .chatbox-messages {
            flex: 1;
            padding: 15px;
            overflow-y: auto;
            display: flex;
            flex-direction: column;
        }

        .message {
            margin-bottom: 10px;
            padding: 10px 15px;
            border-radius: 20px;
            max-width: 80%;
            line-height: 1.4;
            word-wrap: break-word;
        }

        .user-message {
            background-color: #0d6efd;
            color: white;
            align-self: flex-end;
        }

        .ai-message {
            background-color: #e9e9eb;
            color: #333;
            align-self: flex-start;
        }

        .typing-indicator {
            display: none;
            align-self: flex-start;
            margin-bottom: 10px;
            padding: 10px 15px;
        }

        .typing-indicator span {
            height: 8px;
            width: 8px;
            background-color: #ccc;
            border-radius: 50%;
            display: inline-block;
            margin: 0 2px;
            animation: bounce 1.4s infinite ease-in-out both;
        }

        .typing-indicator span:nth-child(1) {
            animation-delay: -0.32s;
        }

        .typing-indicator span:nth-child(2) {
            animation-delay: -0.16s;
        }

        @keyframes bounce {
            0%, 80%, 100% {
                transform: scale(0);
            }
            40% {
                transform: scale(1.0);
            }
        }

        .chatbox-input {
            display: flex;
            padding: 10px;
            border-top: 1px solid #ddd;
        }

        .chatbox-input input {
            flex: 1;
            border: 1px solid #ccc;
            border-radius: 20px;
            padding: 10px 15px;
            margin-right: 10px;
        }

        .chatbox-input button {
            border-radius: 50%;
            width: 40px;
            height: 40px;
        }
    </style>
</head>
<body class="antialiased">

<c:if test="${not empty sessionScope.toastMessage}">
    <div class="position-fixed top-0 end-0 p-3" style="z-index: 1055;">
        <div class="toast align-items-center text-white bg-${sessionScope.toastType} border-0 show" role="alert"
             aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                        ${sessionScope.toastMessage}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"
                        aria-label="Đóng"></button>
            </div>
        </div>
    </div>
    <c:remove var="toastMessage" scope="session"/>
    <c:remove var="toastType" scope="session"/>
</c:if>

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm sticky-top">
    <div class="container">
        <a class="navbar-brand fw-bold text-danger" href="<%= request.getContextPath() %>/index.jsp">CẦM ĐỒ NHANH</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link active fw-bold text-danger"
                                        href="<%= request.getContextPath() %>/index.jsp">Trang chủ</a></li>
                <li class="nav-item"><a class="nav-link"
                                        href="<%= request.getContextPath() %>/liquidation-products">Sản phẩm thanh lý</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/views/about.jsp">Giới
                    thiệu</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/views/contact.jsp">Liên
                    hệ</a></li>

                <c:if test="${empty sessionScope.account}">
                    <li class="nav-item">
                        <a class="btn btn-danger ms-2" href="<%= request.getContextPath() %>/login">
                            <i class="fa-solid fa-right-to-bracket me-1"></i> Đăng nhập
                        </a>
                    </li>
                </c:if>

                <c:if test="${not empty sessionScope.account}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-danger fw-bold" href="#" id="userMenu" role="button"
                           data-bs-toggle="dropdown">
                            <i class="fa-solid fa-user me-1"></i> ${sessionScope.account.username}
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <c:choose>
                                <c:when test="${sessionScope.account.role eq 'ADMIN'}">
                                    <li><a class="dropdown-item"
                                           href="<%= request.getContextPath() %>/admin-home">Trang Quản Trị</a></li>
                                </c:when>
                                <c:when test="${sessionScope.account.role eq 'STAFF'}">
                                    <li><a class="dropdown-item"
                                           href="<%= request.getContextPath() %>/employee-home">Trang Nhân Viên</a>
                                    </li>
                                </c:when>
                                <c:when test="${sessionScope.account.role eq 'USER'}">
                                    <li><a class="dropdown-item"
                                           href="<%= request.getContextPath() %>/customer-home">Tài Khoản Của Tôi</a>
                                    </li>
                                </c:when>
                            </c:choose>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item text-danger" href="<%= request.getContextPath() %>/logout">
                                <i class="fa-solid fa-right-from-bracket me-1"></i> Đăng xuất
                            </a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<section class="hero-bg text-white d-flex align-items-center">
    <div class="hero-overlay"></div>
    <div class="container py-5 position-relative z-1">
        <div class="row">
            <div class="col-lg-7 col-md-9">
                <p class="fs-6 fw-bold text-warning text-uppercase mb-3">Giải Pháp Tài Chính Tức Thì</p>
                <h1 class="display-4 fw-bolder mb-3">
                    Vốn Nhanh, Thủ Tục <span class="text-danger">Siêu Đơn Giản</span>
                </h1>
                <p class="lead text-white mb-4">
                    Chúng tôi cung cấp dịch vụ cầm cố tài sản đa dạng như ô tô, xe máy, trang sức và thiết bị điện tử với
                    lãi suất cạnh tranh.
                </p>
                <div class="d-flex gap-3">
                    <a href="<%= request.getContextPath() %>/views/contact.jsp"
                       class="btn btn-danger btn-lg shadow-lg fw-bold px-4">
                        <i class="fa-solid fa-handshake me-2"></i> Bắt đầu Tư Vấn Ngay
                    </a>
                    <c:if test="${not empty sessionScope.account}">
                        <c:choose>
                            <c:when test="${sessionScope.account.role eq 'ADMIN'}">
                                <a href="<%= request.getContextPath() %>/admin-home"
                                   class="btn btn-outline-light btn-lg shadow-lg fw-bold px-4">
                                    <i class="fa-solid fa-gauge me-2"></i> Vào Trang Quản Trị
                                </a>
                            </c:when>
                            <c:when test="${sessionScope.account.role eq 'STAFF'}">
                                <a href="<%= request.getContextPath() %>/employee-home"
                                   class="btn btn-outline-light btn-lg shadow-lg fw-bold px-4">
                                    <i class="fa-solid fa-briefcase me-2"></i> Trang Nhân Viên
                                </a>
                            </c:when>
                            <c:when test="${sessionScope.account.role eq 'USER'}">
                                <a href="<%= request.getContextPath() %>/customer-home"
                                   class="btn btn-outline-light btn-lg shadow-lg fw-bold px-4">
                                    <i class="fa-solid fa-user me-2"></i> Trang Của Tôi
                                </a>
                            </c:when>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>

<footer class="bg-dark text-white py-4 mt-0">
</footer>

<div class="chat-bubble" id="chat-bubble">
    <i class="fa-solid fa-comments"></i>
</div>

<div class="chatbox" id="chatbox">
    <div class="chatbox-header">
        <div><i class="fa-solid fa-robot"></i> AI Hỗ trợ</div>
        <div>
            <button class="chat-clear-btn" id="chat-clear-btn" title="Bắt đầu lại cuộc trò chuyện">
                <i class="fa-solid fa-trash-arrow-up"></i>
            </button>
            <span class="chatbox-close" id="chatbox-close">&times;</span>
        </div>
    </div>
    <div class="chatbox-messages" id="chatbox-messages">
    </div>
    <div class="typing-indicator" id="typing-indicator">
        <span></span><span></span><span></span>
    </div>
    <div class="chatbox-input">
        <input type="text" id="user-input" placeholder="Nhập câu hỏi của bạn...">
        <button class="btn btn-danger" id="send-button"><i class="fa-solid fa-paper-plane"></i></button>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const chatBubble = document.getElementById("chat-bubble");
        const chatbox = document.getElementById("chatbox");
        const chatboxClose = document.getElementById("chatbox-close");
        const sendButton = document.getElementById("send-button");
        const userInput = document.getElementById("user-input");
        const messagesContainer = document.getElementById("chatbox-messages");
        const typingIndicator = document.getElementById("typing-indicator");
        const clearChatBtn = document.getElementById("chat-clear-btn");

        let conversationHistory = [];

        // --- HÀM ĐỂ LƯU LỊCH SỬ ---
        function saveHistory() {
            localStorage.setItem('groqChatHistory', JSON.stringify(conversationHistory));
        }

        // --- HÀM ĐỂ TẢI LỊCH SỬ ---
        function loadHistory() {
            const savedHistory = localStorage.getItem('groqChatHistory');
            messagesContainer.innerHTML = ''; // Xóa giao diện cũ

            if (savedHistory) {
                conversationHistory = JSON.parse(savedHistory);
                conversationHistory.forEach(message => {
                    const messageType = message.role === 'user' ? 'user-message' : 'ai-message';
                    appendMessage(message.content, messageType);
                });
            } else {
                // Nếu không có lịch sử, tạo tin nhắn chào mừng
                const initialMessage = "Xin chào! Tôi là trợ lý AI của CẦM ĐỒ NHANH. Tôi có thể giúp gì cho bạn?";
                appendMessage(initialMessage, "ai-message");
                conversationHistory = [{ role: "assistant", content: initialMessage }];
                saveHistory();
            }
            scrollToBottom();
        }

        // --- HÀM ĐỂ XÓA LỊCH SỬ ---
        clearChatBtn.addEventListener("click", () => {
            if (confirm("Bạn có chắc muốn xóa toàn bộ lịch sử cuộc trò chuyện này?")) {
                localStorage.removeItem('groqChatHistory');
                loadHistory(); // Tải lại để hiển thị tin nhắn chào mừng mới
            }
        });

        // Mở/đóng chatbox
        chatBubble.addEventListener("click", () => chatbox.classList.toggle("active"));
        chatboxClose.addEventListener("click", () => chatbox.classList.remove("active"));

        // Gửi tin nhắn
        sendButton.addEventListener("click", sendMessage);
        userInput.addEventListener("keypress", (e) => {
            if (e.key === "Enter") {
                sendMessage();
            }
        });

        function sendMessage() {
            const messageText = userInput.value.trim();
            if (messageText === "") return;

            appendMessage(messageText, "user-message");
            userInput.value = "";
            typingIndicator.style.display = 'block';
            scrollToBottom();

            conversationHistory.push({ role: "user", content: messageText });
            saveHistory();

            fetch('${pageContext.request.contextPath}/chat-ai', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(conversationHistory)
            })
                .then(response => {
                    if (!response.ok) { throw new Error('Network response was not ok'); }
                    return response.json();
                })
                .then(data => {
                    const aiText = data.choices[0].message.content;
                    typingIndicator.style.display = 'none';
                    appendMessage(aiText, "ai-message");
                    scrollToBottom();

                    conversationHistory.push({ role: "assistant", content: aiText });
                    saveHistory();
                })
                .catch(error => {
                    console.error('Error:', error);
                    typingIndicator.style.display = 'none';
                    appendMessage("Xin lỗi, đã có lỗi xảy ra. Vui lòng thử lại.", "ai-message");
                    scrollToBottom();
                });
        }

        function appendMessage(text, className) {
            const messageDiv = document.createElement("div");
            messageDiv.classList.add("message", className);
            messageDiv.innerHTML = text.replace(/\n/g, '<br>');
            messagesContainer.appendChild(messageDiv);
        }

        function scrollToBottom() {
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
        }

        // --- TẢI LỊCH SỬ KHI TRANG ĐƯỢC MỞ ---
        loadHistory();
    });
</script>

</body>
</html>