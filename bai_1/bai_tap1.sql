create database student_management;
use student_management;
create table Class(
id int AUTO_INCREMENT PRIMARY KEY,
name Varchar(100) not null
);
create table Teacher(
id int AUTO_INCREMENT PRIMARY KEY,
name Varchar(100) not null,
age INT,
country VARCHAR(100)
);
insert into Class (name)
values ('C0625G1'),('C0625G2');
insert into Teacher(name, age, country)
values ('Nguyen Ngoc Thoi', 11, 'Viet Nam'),
('Tran Van An', 12, 'Brazil');
select * from Class;
select * from Teacher;
