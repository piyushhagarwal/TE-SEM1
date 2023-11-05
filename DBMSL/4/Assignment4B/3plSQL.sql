-- Write a PL/SQL block of code for the following requirements:- 
-- Schema: 
-- 1. Borrower(Rollin, Name, DateofIssue, NameofBook, Status) 
-- 2. Fine(Roll_no,Date,Amt) 
--  Accept roll_no & name of book from user. 
--  Check the number of days (from date of issue), if days are between 15 to 30 then fine amount will be Rs 5per day. 
--  If no. of days>30, per day fine will be Rs 50 per day & for days less than 30, Rs. 5 per day. 
--  After submitting the book, status will change from I to R. 
--  If condition of fine is true, then details will be stored into  fine table

-- Creating PROCEDURE
DELIMITER $$
CREATE PROCEDURE update_fine(IN roll_no INT, IN book_name VARCHAR(20), IN curr_date DATE)
BEGIN
    DECLARE fine_amount INT;
    DECLARE diff_in_dates INT;
    DECLARE issue_date DATE;

    SELECT issuedate INTO issue_date FROM borrower WHERE pk_br_id = roll_no AND bk_name = book_name;

    SET diff_in_dates = curr_date - issue_date;

    IF diff_in_dates BETWEEN 15 AND 30 THEN
        SET fine_amount = 5 * diff_in_dates;
        INSERT INTO fine VALUES(roll_no, curr_date, fine_amount);
        UPDATE borrower SET status = 1 WHERE pk_br_id = roll_no AND bk_name = book_name;
    ELSEIF diff_in_dates > 30 THEN
        SET fine_amount = 50 * diff_in_dates;
        INSERT INTO fine VALUES(roll_no, curr_date, fine_amount);
        UPDATE borrower SET status = 1 WHERE pk_br_id = roll_no AND bk_name = book_name;
    END IF;
END $$
DELIMITER ;

-- Calling PROCEDURE
CALL update_fine(1,'enim','2012-07-25');