-- Write  a  Stored  Procedure  namely  proc_Grade 
-- for the categorization of student. If marks scored 
-- by students in examination is <=1500 and 
-- marks>=990 then student will be placed in 
-- distinction category if marks scored are between 
-- 989 and900 category is first class, if 
-- marks899and 825  category  is  Higher  Second 
-- Class.

DELIMITER $$
CREATE PROCEDURE proc_Grade(IN student_name VARCHAR(20))
BEGIN
    DECLARE student_marks INT;

    SELECT stud_marks INTO student_marks FROM StudentMarks WHERE stud_name = student_name;

    IF student_marks <= 1500 AND student_marks >= 990 THEN
        INSERT INTO Category VALUES(student_name,'Distinction');
    ELSEIF student_marks <= 989 AND student_marks >= 900 THEN
        INSERT INTO Category VALUES(student_name,'First Class');
    ELSEIF student_marks <= 899 AND student_marks >= 825 THEN
        INSERT INTO Category VALUES(student_name,'Second Class');
    END IF;
END $$
DELIMITER ;

--Call Procedure
CALL proc_Grade('necessitatibus');

    
        


    


