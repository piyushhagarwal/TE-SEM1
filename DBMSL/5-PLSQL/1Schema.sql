-- Write  a  Stored  Procedure  namely  proc_Grade 
-- for the categorization of student. If marks scored 
-- by students in examination is <=1500 and 
-- marks>=990 then student will be placed in 
-- distinction category if marks scored are between 
-- 989 and900 category is first class, if 
-- marks899and 825  category  is  Higher  Second 
-- Class. 

CREATE TABLE StudentMarks(
    stud_name VARCHAR(20) PRIMARY KEY,
    stud_marks INT
);

CREATE TABLE Category(
    stud_name VARCHAR(20),
    category_name VARCHAR(20),
    FOREIGN KEY(stud_name) REFERENCES StudentMarks(stud_name)
);