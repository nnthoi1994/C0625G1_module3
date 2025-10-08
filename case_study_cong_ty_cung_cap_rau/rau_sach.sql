
CREATE DATABASE IF NOT EXISTS quan_ly_hang_hoa2;
USE quan_ly_hang_hoa2;


CREATE TABLE IF NOT EXISTS nhom_hang (
    ma_loai_hang INT AUTO_INCREMENT PRIMARY KEY,
    ten_loai_hang VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS hang_hoa (
    ma_hang INT AUTO_INCREMENT PRIMARY KEY,
    ten_hang_hoa VARCHAR(255) NOT NULL,
    don_vi_tinh enum('kg', 'túi', 'bó'),
    don_gia INT NOT NULL,
    ma_loai_hang INT NOT NULL,
    FOREIGN KEY (ma_loai_hang) REFERENCES nhom_hang(ma_loai_hang) ON DELETE CASCADE
);



INSERT INTO nhom_hang (ten_loai_hang) VALUES
('Rau'),
('Củ'),
('Quả'),
('Hoa');


INSERT INTO hang_hoa (ten_hang_hoa, don_vi_tinh, don_gia, ma_loai_hang) VALUES
('Cà chua', 'kg', 20000,3),
('Rau muống', 'Bó', 20000,1),
('Tỏi', 'kg', 20000,2);

SELECT * FROM quan_ly_hang_hoa2.nhom_hang;
SELECT * FROM quan_ly_hang_hoa2.hang_hoa;