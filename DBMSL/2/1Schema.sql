CREATE TABLE Dept(
    Dept_id INT PRIMARY KEY,
    Dept_name VARCHAR(20),
    Dept_location VARCHAR(20)
);

-- Alternative way
-- CREATE TABLE Dept(
--     Dept_id INT,
--     Dept_name VARCHAR(20),
--     Dept_location VARCHAR(20),
--     PRIMARY KEY(Dept_id)
-- );

CREATE TABLE Employee(
    Emp_id INT PRIMARY KEY,
    Dept_id INT,
    Emp_fname VARCHAR(20),
    Emp_lname VARCHAR(20),
    Emp_position VARCHAR(20),
    Emp_salary INT,
    Emp_JoinDate DATE,
    FOREIGN KEY(Dept_id) REFERENCES Dept(Dept_id)
);

CREATE TABLE Project(
    Proj_id INT PRIMARY KEY,
    Dept_id INT,
    Proj_name VARCHAR(20),
    Proj_location VARCHAR(20),
    Proj_cost INT,
    Proj_year INT,
    FOREIGN KEY(Dept_id) REFERENCES Dept(Dept_id)
);