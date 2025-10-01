
CREATE DATABASE IF NOT EXISTS furama_resort;
USE furama_resort;


CREATE TABLE IF NOT EXISTS vi_tri (
  ma_vi_tri INT PRIMARY KEY AUTO_INCREMENT,
  ten_vi_tri VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS trinh_do (
  ma_trinh_do INT PRIMARY KEY AUTO_INCREMENT,
  ten_trinh_do VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS bo_phan (
  ma_bo_phan INT PRIMARY KEY AUTO_INCREMENT,
  ten_bo_phan VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS nhan_vien (
  ma_nhan_vien INT PRIMARY KEY AUTO_INCREMENT,
  ho_ten VARCHAR(100),
  ngay_sinh DATE,
  so_cmnd VARCHAR(30),
  so_dien_thoai VARCHAR(30),
  email VARCHAR(100),
  dia_chi VARCHAR(200),
  ma_trinh_do INT,
  ma_vi_tri INT,
  ma_bo_phan INT,
  FOREIGN KEY (ma_trinh_do) REFERENCES trinh_do(ma_trinh_do),
  FOREIGN KEY (ma_vi_tri) REFERENCES vi_tri(ma_vi_tri),
  FOREIGN KEY (ma_bo_phan) REFERENCES bo_phan(ma_bo_phan)
);


CREATE TABLE IF NOT EXISTS loai_khach (
  ma_loai_khach INT PRIMARY KEY AUTO_INCREMENT,
  ten_loai_khach VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS khach_hang (
  ma_khach_hang INT PRIMARY KEY AUTO_INCREMENT,
  ho_ten VARCHAR(100),
  ngay_sinh DATE,
  gioi_tinh TINYINT,
  so_cmnd VARCHAR(30),
  so_dien_thoai VARCHAR(30),
  email VARCHAR(100),
  dia_chi VARCHAR(200),
  ma_loai_khach INT,
  FOREIGN KEY (ma_loai_khach) REFERENCES loai_khach(ma_loai_khach)
);


CREATE TABLE IF NOT EXISTS kieu_thue (
  ma_kieu_thue INT PRIMARY KEY AUTO_INCREMENT,
  ten_kieu_thue VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS loai_dich_vu (
  ma_loai_dich_vu INT PRIMARY KEY AUTO_INCREMENT,
  ten_loai_dich_vu VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS dich_vu (
  ma_dich_vu INT PRIMARY KEY AUTO_INCREMENT,
  ten_dich_vu VARCHAR(100),
  dien_tich DOUBLE,
  chi_phi_thue DOUBLE,
  so_nguoi_toi_da INT,
  ma_kieu_thue INT,
  ma_loai_dich_vu INT,
  FOREIGN KEY (ma_kieu_thue) REFERENCES kieu_thue(ma_kieu_thue),
  FOREIGN KEY (ma_loai_dich_vu) REFERENCES loai_dich_vu(ma_loai_dich_vu)
);


CREATE TABLE IF NOT EXISTS dich_vu_di_kem (
  ma_dich_vu_di_kem INT PRIMARY KEY AUTO_INCREMENT,
  ten_dich_vu_di_kem VARCHAR(100),
  don_vi VARCHAR(20),
  gia DOUBLE
);


CREATE TABLE IF NOT EXISTS hop_dong (
  ma_hop_dong INT PRIMARY KEY AUTO_INCREMENT,
  ngay_lam_hop_dong DATE,
  ngay_ket_thuc DATE,
  tien_dat_coc DOUBLE,
  tong_tien DOUBLE,
  ma_nhan_vien INT,
  ma_khach_hang INT,
  ma_dich_vu INT,
  FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien(ma_nhan_vien),
  FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang(ma_khach_hang),
  FOREIGN KEY (ma_dich_vu) REFERENCES dich_vu(ma_dich_vu)
);


CREATE TABLE IF NOT EXISTS hop_dong_chi_tiet (
  ma_hop_dong_chi_tiet INT PRIMARY KEY AUTO_INCREMENT,
  so_luong INT,
  ma_hop_dong INT,
  ma_dich_vu_di_kem INT,
  FOREIGN KEY (ma_hop_dong) REFERENCES hop_dong(ma_hop_dong),
  FOREIGN KEY (ma_dich_vu_di_kem) REFERENCES dich_vu_di_kem(ma_dich_vu_di_kem)
);


INSERT INTO vi_tri(ten_vi_tri) VALUES ('Lễ tân'),('Phục vụ'),('Quản lý'),('Kỹ thuật');
INSERT INTO trinh_do(ten_trinh_do) VALUES ('Trung cấp'),('Cao đẳng'),('Đại học'),('Sau đại học');
INSERT INTO bo_phan(ten_bo_phan) VALUES ('Sale – Marketing'),('Hành Chính'),('Phục vụ'),('Quản lý');

INSERT INTO loai_khach(ten_loai_khach) VALUES ('Diamond'),('Platinium'),('Gold'),('Silver'),('Member');
INSERT INTO kieu_thue(ten_kieu_thue) VALUES ('Theo giờ'),('Theo ngày'),('Theo tháng'),('Theo năm');
INSERT INTO loai_dich_vu(ten_loai_dich_vu) VALUES ('Villa'),('House'),('Room');

INSERT INTO dich_vu(ten_dich_vu,dien_tich,chi_phi_thue,so_nguoi_toi_da,ma_kieu_thue,ma_loai_dich_vu)
 VALUES 
 ('Furama Villa A',150,2000000,6,2,1),
 ('Furama House B',100,1200000,4,2,2),
 ('Furama Room C',35,400000,2,2,3);

INSERT INTO dich_vu_di_kem(ten_dich_vu_di_kem,don_vi,gia) VALUES
('Massage','lần',200000),('Karaoke','giờ',150000),('Thuê xe','ngày',500000),('Thức ăn','suất',80000);


INSERT INTO nhan_vien(ho_ten,ngay_sinh,so_cmnd,so_dien_thoai,email,dia_chi,ma_trinh_do,ma_vi_tri,ma_bo_phan)
 VALUES ('Hoang Anh','1990-05-10','123456789','0909999000','ha@example.com','Hải Châu',3,1,1),
        ('Thai Minh','1985-11-20','987654321','0911111222','tm@example.com','Liên Chiểu',2,2,2);

INSERT INTO khach_hang(ho_ten,ngay_sinh,gioi_tinh,so_cmnd,so_dien_thoai,email,dia_chi,ma_loai_khach)
 VALUES ('Nguyen Van A','1995-03-01',1,'111222333','0988888777','a@example.com','Đà Nẵng',1),
        ('Le Thi B','1975-07-07',0,'222333444','0977777666','b@example.com','Quảng Trị',2);

INSERT INTO hop_dong(ngay_lam_hop_dong,ngay_ket_thuc,tien_dat_coc,tong_tien,ma_nhan_vien,ma_khach_hang,ma_dich_vu)
 VALUES ('2020-10-15','2020-10-20',500000,2700000,1,1,1),
        ('2021-01-05','2021-01-10',400000,1600000,2,2,3);

INSERT INTO hop_dong_chi_tiet(so_luong,ma_hop_dong,ma_dich_vu_di_kem)
 VALUES (2,1,1),(1,1,2),(3,2,4);

-- Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự 
-- “H”, “T” hoặc “K” và có tối đa 15 kí tự.
SELECT *
FROM nhan_vien
WHERE (ho_ten LIKE 'H%' OR ho_ten LIKE 'T%' OR ho_ten LIKE 'K%')
  AND CHAR_LENGTH(ho_ten) <= 15;

-- Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
SELECT *
FROM khach_hang
WHERE TIMESTAMPDIFF(YEAR, ngay_sinh, CURDATE()) BETWEEN 18 AND 50
  AND dia_chi IN ('Đà Nẵng','Quảng Trị');

-- Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng. Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
SELECT kh.ma_khach_hang, kh.ho_ten, COUNT(hd.ma_hop_dong) AS so_lan_dat
FROM khach_hang kh
JOIN loai_khach lk ON kh.ma_loai_khach = lk.ma_loai_khach
LEFT JOIN hop_dong hd ON kh.ma_khach_hang = hd.ma_khach_hang
WHERE lk.ten_loai_khach = 'Diamond'
GROUP BY kh.ma_khach_hang, kh.ho_ten
ORDER BY so_lan_dat ASC;

SELECT kh.ma_khach_hang, kh.ho_ten, lk.ten_loai_khach,
       hd.ma_hop_dong, dv.ten_dich_vu, hd.ngay_lam_hop_dong, hd.ngay_ket_thuc,
       (IFNULL(dv.chi_phi_thue,0) + IFNULL(SUM(hdct.so_luong * dvdk.gia),0)) AS tong_tien_tinh
FROM khach_hang kh
LEFT JOIN loai_khach lk ON kh.ma_loai_khach = lk.ma_loai_khach
LEFT JOIN hop_dong hd ON kh.ma_khach_hang = hd.ma_khach_hang
LEFT JOIN dich_vu dv ON hd.ma_dich_vu = dv.ma_dich_vu
LEFT JOIN hop_dong_chi_tiet hdct ON hd.ma_hop_dong = hdct.ma_hop_dong
LEFT JOIN dich_vu_di_kem dvdk ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
GROUP BY kh.ma_khach_hang, hd.ma_hop_dong;


SELECT dv.ma_dich_vu, dv.ten_dich_vu, dv.dien_tich, dv.chi_phi_thue, ldv.ten_loai_dich_vu
FROM dich_vu dv
JOIN loai_dich_vu ldv ON dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
WHERE dv.ma_dich_vu NOT IN (
    SELECT DISTINCT ma_dich_vu FROM hop_dong
    WHERE YEAR(ngay_lam_hop_dong) = 2021 AND MONTH(ngay_lam_hop_dong) IN (1,2,3)
);


SELECT DISTINCT dv.ma_dich_vu, dv.ten_dich_vu, dv.dien_tich, dv.so_nguoi_toi_da, dv.chi_phi_thue, ldv.ten_loai_dich_vu
FROM dich_vu dv
JOIN loai_dich_vu ldv ON dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
WHERE dv.ma_dich_vu IN (
  SELECT DISTINCT ma_dich_vu FROM hop_dong WHERE YEAR(ngay_lam_hop_dong) = 2020
)
AND dv.ma_dich_vu NOT IN (
  SELECT DISTINCT ma_dich_vu FROM hop_dong WHERE YEAR(ngay_lam_hop_dong) = 2021
);


-- Cách 1: DISTINCT
SELECT DISTINCT ho_ten FROM khach_hang;

-- Cách 2: GROUP BY
SELECT ho_ten FROM khach_hang GROUP BY ho_ten;

-- Cách 3: Subquery (lấy 1 record đại diện cho mỗi tên)
SELECT ho_ten FROM khach_hang kh
WHERE ma_khach_hang = (
  SELECT MIN(ma_khach_hang) FROM khach_hang WHERE ho_ten = kh.ho_ten
);


