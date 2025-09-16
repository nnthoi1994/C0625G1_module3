create database quan_ly_ban_hang;
use quan_ly_ban_hang;
create table customer(
cID varchar(20) primary key,
cName varchar(50),
cAge int
);

create table pOrder(
oID varchar(20) primary key,
cID varchar(20),
oDate datetime,
oTotalPrice int,
foreign key (cID) references customer(cID)
);

create table product(
pID varchar(20) primary key,
pName varchar(50),
pPrice int
);

create table OrderDetail(
oID varchar(20),
pID varchar(20),
odQTY varchar(20),
primary key (oID,pID),
foreign key (oID) references pOrder(oID),
foreign key (pID) references product(pID)
);	


