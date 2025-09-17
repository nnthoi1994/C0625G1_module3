CREATE DATABASE IF NOT EXISTS school_demo;
USE school_demo;

-- Bảng lớp học
CREATE TABLE Class (
    ClassID INT PRIMARY KEY AUTO_INCREMENT,
    ClassName VARCHAR(50),
    StartDate DATE
);

-- Bảng sinh viên
CREATE TABLE Student (
    StudentID INT PRIMARY KEY AUTO_INCREMENT,
    StudentName VARCHAR(50),
    Gender BOOLEAN,
    Birthday DATE,
    ClassID INT,
    FOREIGN KEY (ClassID) REFERENCES Class(ClassID)
);

-- Bảng môn học
CREATE TABLE Subject (
    SubID INT PRIMARY KEY AUTO_INCREMENT,
    SubName VARCHAR(50),
    Credit INT
);

-- Bảng điểm thi
CREATE TABLE Mark (
    MarkID INT PRIMARY KEY AUTO_INCREMENT,
    StudentID INT,
    SubID INT,
    Mark FLOAT,
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (SubID) REFERENCES Subject(SubID)
);

-- Thêm lớp
INSERT INTO Class (ClassName, StartDate) VALUES
('C1121G1', '2021-12-05'),
('C1221G1', '2021-11-15'),
('A0821I1', '2021-12-20');

-- Thêm sinh viên
INSERT INTO Student (StudentName, Gender, Birthday, ClassID) VALUES
('Hung', 1, '2000-05-10', 1),
('Hoa', 0, '1999-08-15', 1),
('Huy', 1, '2001-02-20', 2),
('Lan', 0, '2000-07-30', 3),
('Hai', 1, '1998-03-22', 3);

-- Thêm môn học
INSERT INTO Subject (SubName, Credit) VALUES
('Math', 3),
('Physics', 4),
('Chemistry', 5),
('History', 2);

-- Thêm điểm thi
INSERT INTO Mark (StudentID, SubID, Mark) VALUES
(1, 3, 8),
(1, 2, 7.0),
(2, 1, 9.0),
(3, 3, 6.0),
(4, 2, 7.5),
(5, 1, 8.0);

SELECT * FROM Class;
SELECT * FROM Student;
SELECT * FROM Subject;
SELECT * FROM Mark;

-- Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’
select *
from student
where studentname like 'h%';

-- Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.
select*
from class
where month(startdate) = 12;

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.
select*
from subject
where credit>=3 and credit <=5;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.
update student
set classid = 2
where studentname = 'hung'
limit 1;
-- Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
select stu.studentname, sub.subname, m.mark
from mark m
join student stu on stu.studentid = m.studentid
join subject sub on sub.subid = m.subid
order by m.mark desc, stu.studentname asc;
