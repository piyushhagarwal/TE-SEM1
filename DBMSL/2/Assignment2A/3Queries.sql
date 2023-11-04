-- 1. Insert at least 10 records in the Employee table and insert other tables accordingly.

-- 2. Display all Employee details with Department ‘Computer’ and ‘IT’ and Employee first name starting with 'p' or 'h'.
SELECT * FROM Employee 
WHERE (Dept_id IN (SELECT Dept_id FROM Dept WHERE Dept_name = 'Computer' OR Dept_name = 'IT'))
AND (Emp_fname LIKE 'p%' OR Emp_fname LIKE 'h%');

-- 3. lists the number of different Employee Positions.
SELECT COUNT(DISTINCT Emp_Position) FROM Employee;

-- Give 10% increase in Salary of the Employee whose join date is before 1985.
UPDATE Employee
SET Emp_salary = Emp_salary * 1.1
WHERE YEAR(Emp_JoinDate) < 1985;

-- 5. Delete Department details which location is ‘Mumbai’.
DELETE FROM Dept
WHERE Dept_location = 'Mumbai';

-- 6. Find the names of Projects with location ‘pune’ .
SELECT Proj_Name FROM Project
WHERE Proj_location = 'pune';

-- 7. Find the project having cost in between 100000 to 500000.
SELECT * FROM Project
WHERE Proj_Cost BETWEEN 100000 AND 500000;

-- 8. Find the project having maximum price and find average of Project cost
SELECT *,(SELECT AVG(Proj_Cost) FROM Project) FROM Project
ORDER BY Proj_Cost DESC
LIMIT 1;

-- 9. Display all employees with Emp _id and Emp name in decreasing order of Emp_lname
SELECT Emp_id,Emp_fname, Emp_lname FROM Employee 
ORDER BY Emp_lname DESC;

-- 10. Display Proj_name,Proj_location ,Proj_cost of all project started in 2004,2005,2007
SELECT Proj_name, Proj_location, Proj_cost FROM Project 
WHERE Proj_year IN (2004,2005,2007);