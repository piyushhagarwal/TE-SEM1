-- Write a database trigger on Library table. The System should keep track of the
-- records that are being updated or deleted. The old value of updated or deleted
-- records should be added in Library_Audit table.

CREATE TABLE Library(
    stu_roll_no INT PRIMARY KEY,
    stu_name VARCHAR(20),
    book_name VARCHAR(20),
    date_of_issue DATE,
    price INT
);

CREATE TABLE Library_Audit(
    Action_Performed VARCHAR(20),
    stu_roll_no INT ,
    stu_name VARCHAR(20),
    book_name VARCHAR(20),
    date_of_issue DATE,
    price INT,
    FOREIGN KEY (stu_roll_no) REFERENCES Library(stu_roll_no) ON DELETE CASCADE
);