CREATE DATABASE bai_tap_4;
USE bai_tap_4;
CREATE TABLE Products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    productCode VARCHAR(20) NOT NULL,
    productName VARCHAR(50) NOT NULL,
    productPrice DECIMAL(10, 2) NOT NULL,
    productAmount INT NOT NULL,
    productDescription TEXT,
    productStatus VARCHAR(20)
);

INSERT INTO Products (productCode, productName, productPrice, productAmount, productDescription, productStatus)
VALUES
('P101', 'Laptop', 1200.00, 50, 'High-performance laptop', 'Available'),
('P102', 'Smartphone', 800.00, 150, 'Latest model smartphone', 'Available'),
('P103', 'Headphones', 150.00, 200, 'Noise-cancelling headphones', 'Out of Stock'),
('P104', 'Monitor', 300.00, 80, '4K Ultra HD monitor', 'Available');

select * from products;

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
CREATE UNIQUE INDEX idx_productCode ON Products(productCode);
DROP INDEX idx_productCode ON Products;

-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
CREATE INDEX idx_productName_price ON Products(productName, productPrice);
DROP INDEX idx_productName_price ON Products;

-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
-- So sánh câu truy vấn trước và sau khi tạo index
EXPLAIN SELECT * FROM Products WHERE productcode = 'P101';
EXPLAIN SELECT * FROM Products WHERE productName = 'Smartphone' AND productPrice = 800.00;

-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
CREATE VIEW product_info AS
SELECT productCode, productName, productPrice, productStatus
FROM Products;

-- Để xem view vừa tạo:
SELECT * FROM product_info;

-- Tiến hành sửa đổi view
ALTER VIEW product_info AS
SELECT productCode, productName, productPrice
FROM Products
WHERE productStatus = 'Available';

-- Xem view đã sửa đổi:
SELECT * FROM product_info;

-- Tiến hành xoá view
DROP VIEW product_info;

-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
DELIMITER //
CREATE PROCEDURE GetAllProducts()
BEGIN
    SELECT * FROM Products;
END //
DELIMITER ;

-- Để gọi procedure:
CALL GetAllProducts();

-- Tạo store procedure thêm một sản phẩm mới
DELIMITER //
CREATE PROCEDURE AddNewProduct(
    IN p_productCode VARCHAR(20),
    IN p_productName VARCHAR(50),
    IN p_productPrice DECIMAL(10, 2),
    IN p_productAmount INT,
    IN p_productDescription TEXT,
    IN p_productStatus VARCHAR(20)
)
BEGIN
    INSERT INTO Products (productCode, productName, productPrice, productAmount, productDescription, productStatus)
    VALUES (p_productCode, p_productName, p_productPrice, p_productAmount, p_productDescription, p_productStatus);
END //
DELIMITER ;

-- Để gọi procedure:
CALL AddNewProduct('P105', 'Mouse', 25.00, 300, 'Wireless ergonomic mouse', 'Available');

-- Tạo store procedure sửa thông tin sản phẩm theo id
DELIMITER //
CREATE PROCEDURE UpdateProduct(
    IN p_id INT,
    IN p_productName VARCHAR(50),
    IN p_productPrice DECIMAL(10, 2)
)
BEGIN
    UPDATE Products
    SET productName = p_productName, productPrice = p_productPrice
    WHERE id = p_id;
END //
DELIMITER ;

-- Để gọi procedure:
CALL UpdateProduct(1, 'Laptop Pro', 1500.00);

-- Tạo store procedure xoá sản phẩm theo id
DELIMITER //
CREATE PROCEDURE DeleteProduct(
    IN p_id INT
)
BEGIN
    DELETE FROM Products
    WHERE id = p_id;
END //
DELIMITER ;

-- Để gọi procedure:
CALL DeleteProduct(4);

