--Insert Trigger
DELIMITER $$
CREATE TRIGGER insert_trigger
AFTER INSERT ON Library
FOR EACH ROW
BEGIN
    INSERT INTO Library_Audit VALUES
        ('INSERTION', NEW.stu_roll_no, NEW.stu_name, NEW.book_name, NEW.date_of_issue, NEW.price);
END $$
DELIMITER ;

-- Check Insert Trigger
INSERT INTO Library VALUES
 (11, 'Piyush', 'DBMS', '2023-07-21', 500);

--Update TRIGGER
DELIMITER $$
CREATE TRIGGER update_trigger
AFTER UPDATE ON Library
FOR EACH ROW
BEGIN 
    INSERT INTO Library_Audit VALUES
        ('UPDATION', OLD.stu_roll_no, OLD.stu_name, OLD.book_name, OLD.date_of_issue, OLD.price);
END $$
DELIMITER ;

-- Check Update Trigger
UPDATE Library
SET price = 600
WHERE stu_roll_no = 1;

--Delete Trigger
DELIMITER $$
CREATE TRIGGER delete_trigger
BEFORE DELETE ON Library
FOR EACH ROW
BEGIN
    INSERT INTO Library_Audit VALUES
        ('DELETION', OLD.stu_roll_no, OLD.stu_name, OLD.book_name, OLD.date_of_issue, OLD.price);
END $$
DELIMITER ;

-- Check Delete Trigger
DELETE FROM Library
WHERE stu_roll_no = 10;