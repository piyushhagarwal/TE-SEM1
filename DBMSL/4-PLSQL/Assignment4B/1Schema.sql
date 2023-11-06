-- Write a PL/SQL block of code for the following requirements:- 
-- Schema: 
-- 1. Borrower(Rollin, Name, DateofIssue, NameofBook, Status) 
-- 2. Fine(Roll_no,Date,Amt) 
--  Accept roll_no & name of book from user. 
--  Check the number of days (from date of issue), if days are between 15 to 30 then fine amount will be Rs 5per day. 
--  If no. of days>30, per day fine will be Rs 50 per day & for days less than 30, Rs. 5 per day. 
--  After submitting the book, status will change from I to R. 
--  If condition of fine is true, then details will be stored into  fine table

CREATE TABLE borrower (
	pk_br_id INT PRIMARY KEY ,
	br_name VARCHAR(50),
	issuedate DATE,
	bk_name VARCHAR(50),
	status BOOL
);

CREATE TABLE fine (
	fk_br_id INT,
	finedate DATE,
	amount INT,
	FOREIGN KEY (fk_br_id) REFERENCES borrower(pk_br_id) ON DELETE CASCADE
);