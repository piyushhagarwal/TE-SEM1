-- Write  a  Stored  Procedure  namely  proc_Grade 
-- for the categorization of student. If marks scored 
-- by students in examination is <=1500 and 
-- marks>=990 then student will be placed in 
-- distinction category if marks scored are between 
-- 989 and900 category is first class, if 
-- marks899and 825  category  is  Higher  Second 
-- Class.


-- Creating a Procedure
DELIMITER $$
CREATE PROCEDURE proc_Grade(IN student_name VARCHAR(20))
BEGIN
    DECLARE student_marks INT;
    Declare EXIT HANDLER FOR NOT FOUND SELECT 'No Student Record Found' AS 'Error';

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


-- Creating a Function
DELIMITER $$
CREATE FUNCTION func_Grade(student_marks)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
    DECLARE Student_Grade VARCHAR(20) DEFAULT 'PASS';

    IF student_marks <= 1500 AND student_marks >= 990 THEN
        SET Student_Grade = 'Distinction';
    ELSEIF student_marks <= 989 AND student_marks >= 900 THEN
        SET Student_Grade = 'First Class';
    ELSEIF student_marks <= 899 AND student_marks >= 825 THEN
        SET Student_Grade = 'Second Class';
    END IF;

    RETURN Student_Grade;
END $$
DELIMITER ;

--Call Procedure
CALL proc_Grade('necessitatibus');

    
        


    


