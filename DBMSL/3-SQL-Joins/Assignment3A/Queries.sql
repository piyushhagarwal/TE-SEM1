-- 1. Find Employee details and Department details using NATURAL JOIN.
SELECT * FROM Employee E
JOIN Dept D ON E.Dept_id = D.Dept_id;

-- 2. Find the emp_fname,Emp_position,location,Emp_JoinDate who have same Dept id. 
SELECT Emp_fname,Emp_position,D.Dept_location,Emp_JoinDate FROM Employee E
JOIN Dept D ON E.Dept_id = D.Dept_id
ORDER BY E.Dept_id;

-- 3. Find the Employee details ,Proj_id,Project cost who does not have Project       
-- location as ‘Hyderabad’.
SELECT E.*, P.Proj_id, P.Proj_cost FROM Employee E
JOIN Project P ON E.Dept_id = P.Dept_id
WHERE Proj_location != 'Hyderabad';

-- 4. Find Department Name ,employee name, Emp_position for which project year is 2020
SELECT D.Dept_name, E.Emp_fname, E.Emp_position FROM Employee E
JOIN Project P ON E.Dept_id = P.Dept_id
JOIN Dept D ON E.Dept_id = D.Dept_id
WHERE P.Proj_year = 2020;

-- 5. Display emp_position,D_name who have Project cost >30000
SELECT D.Dept_name, E.Emp_position FROM Employee E
JOIN Project P ON E.Dept_id = P.Dept_id
JOIN Dept D ON E.Dept_id = D.Dept_id
WHERE P.Proj_cost > 30000;

-- 6.Find the names of all the Projects that started in the year 2015.
SELECT Proj_name FROM Project
WHERE Proj_year = 2015;

-- 7. List the Dept_name having no_of_emp=10 
SELECT D.Dept_name FROM Dept D
JOIN Employee E ON E.Dept_id = D.Dept_id
GROUP BY D.Dept_name HAVING COUNT(E.Emp_id) > 10; 

-- 8.Display the total number of employee who have joined  any project before 2009 
SELECT COUNT(DISTINCT E.Emp_id) FROM Employee E
JOIN Project P ON E.Dept_id = P.Dept_id
WHERE Proj_year < 2009;

-- 9. Create a view showing the employee  and Department details.
-- Create a view named EmployeeDeptView
CREATE VIEW EmployeeDeptView AS
SELECT
    E.Emp_id,
    E.Emp_fname,
    E.Emp_lname,
    E.Emp_position,
    E.Emp_salary,
    E.Emp_JoinDate,
    D.Dept_id,
    D.Dept_name,
    D.Dept_location
FROM
    Employee E
JOIN
    Dept D ON E.Dept_id = D.Dept_id;
