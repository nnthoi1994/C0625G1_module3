CREATE DATABASE IF NOT EXISTS SaleManagement;
USE SaleManagement;

-- Bảng Customer
CREATE TABLE Customer (
    cID INT PRIMARY KEY,
    Name VARCHAR(25),
    cAge TINYINT
);

-- Bảng Order (đặt tên order hơi nhạy, nên mình để OrderTable)
CREATE TABLE OrderTable (
    oID INT PRIMARY KEY,
    cID INT,
    oDate DATETIME,
    oTotalPrice INT,
    FOREIGN KEY (cID) REFERENCES Customer(cID)
);

-- Bảng Product
CREATE TABLE Product (
    pID INT PRIMARY KEY,
    pName VARCHAR(25),
    pPrice INT
);

-- Bảng OrderDetail
CREATE TABLE OrderDetail (
    oID INT,
    pID INT,
    odQTY INT,
    FOREIGN KEY (oID) REFERENCES OrderTable(oID),
    FOREIGN KEY (pID) REFERENCES Product(pID)
);

-- Customer
INSERT INTO Customer (cID, Name, cAge) VALUES
(1, 'Minh Quan', 10),
(2, 'Ngoc Oanh', 20),
(3, 'Hong Ha', 50);

-- Order
INSERT INTO OrderTable (oID, cID, oDate, oTotalPrice) VALUES
(1, 1, '2006-03-21', NULL),
(2, 2, '2006-03-23', NULL),
(3, 1, '2006-03-16', NULL);

-- Product
INSERT INTO Product (pID, pName, pPrice) VALUES
(1, 'May Giat', 3),
(2, 'Tu Lanh', 5),
(3, 'Dieu Hoa', 7),
(4, 'Quat', 1),
(5, 'Bep Dien', 2);

-- OrderDetail
INSERT INTO OrderDetail (oID, pID, odQTY) VALUES
(1, 1, 3),
(1, 3, 7),
(1, 4, 2),
(2, 1, 1),
(3, 1, 8),
(2, 5, 4),
(2, 3, 3);

select *
from customer;
select *
from product;
select *
from ordertable;
select *
from orderdetail;

-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select oID, oDate, oTotalPrice
from ordertable;

-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select c.name, p.pname
from customer c
join ordertable ot on ot.cid=c.cid
join orderdetail od on od.oid = ot.oid
join product p on p.pid = od.pid;


-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select c.name
from customer c
left join ordertable ot on c.cid=ot.cid
where ot.oid is null;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được tính = odQTY*pPrice)
select ot.oid, ot.odate, sum(od.odqty*p.pprice) as TotalPice
from orderdetail od
join product p on p.pid=od.pid
join ordertable ot on ot.oid = od.oid
group by ot.oid, ot.odate;