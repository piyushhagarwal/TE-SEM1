-- 1. Insert at least 10 records in the Student table and insert other tables accordingly.

-- 2. Display all students details with branch ‘Computer ‘and ‘It’ and student name starting with 'a' or 'd'.
SELECT * FROM Student 
WHERE (S_branch = 'Computer' OR S_branch = 'IT')
AND (S_name LIKE 'a%' OR S_name LIKE 'd%');

-- 3. list the number of different companies.(use of distinct)
SELECT DISTINCT Pcompany_name FROM PlacemetDrive;

-- 4. Give 15% increase in fee of the Training whose joining year is 2019.
UPDATE Training
SET T_Fee = T_Fee * 1.15
WHERE T_Year = 2019;

-- 5. Delete Student details having CGPA score less than 7.
DELETE FROM Student
WHERE CGPA < 7;

-- 6. Find the names of companies belonging to pune or Mumbai
SELECT Pcompany_name FROM PlacemetDrive
WHERE location = 'Pune' OR location = 'Mumbai';

-- 7. Find the student name who joined training in 1-1-2019 as well as in 1-1-2021
SELECT S_name FROM Student
WHERE T_id IN (
    SELECT T_id
    FROM Training
    WHERE T_year = 2019
) AND T_id IN (
    SELECT T_id
    FROM Training
    WHERE T_year = 2021
);

-- 8. Find the student name having maximum CGPA score and names of students
-- having CGPA score between 7 to 9 .
-- Student with maximum CGPA
SELECT S_name
FROM Student
WHERE CGPA = (SELECT MAX(CGPA) FROM Student);

-- Students with CGPA between 7 and 9
SELECT S_name
FROM Student
WHERE CGPA BETWEEN 7 AND 9;


-- 9. Display all Student name with T_id with decreasing order of Fees
SELECT S_name, S.T_id, T.T_Fee FROM Student S
JOIN Training T ON S.T_id = T.T_id
ORDER BY T.T_Fee DESC;

-- Display PCompany name, S_name ,location and Package with Package 30K,
-- 40K and 50k
SELECT PD.Pcompany_name, S.S_name, PD.Location, PD.Package
FROM PlacemetDrive PD
JOIN Student S ON PD.Drive_id = S.Drive_id
WHERE PD.Package IN (30000, 40000, 50000);
