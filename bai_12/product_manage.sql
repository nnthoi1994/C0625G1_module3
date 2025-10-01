-- Bước 1: Tạo Database (nếu chưa tồn tại)
CREATE DATABASE IF NOT EXISTS product_management;
USE product_management;

-- Bước 2: Tạo Bảng 'categories' (danh mục)
CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Bước 3: Tạo Bảng 'products' (sản phẩm)
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- Bước 4: Thêm dữ liệu mẫu vào các bảng
-- Thêm các danh mục mẫu
INSERT INTO categories (name) VALUES
('Điện tử'),
('Sách'),
('Đồ gia dụng');

-- Thêm các sản phẩm mẫu
INSERT INTO products (name, price, category_id) VALUES
('Điện thoại thông minh', 25000000, 1),
('Máy tính xách tay', 35000000, 1),
('Bố già', 150000, 2),
('Tấm cám', 120000, 2),
('Máy giặt', 7500000, 3),
('Lò vi sóng', 1200000, 3);