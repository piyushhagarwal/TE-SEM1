CREATE TABLE PlacemetDrive(
    Drive_id INT PRIMARY KEY,
    Pcompany_name VARCHAR(20),
    Package INT,
    Location VARCHAR(20)
);

CREATE TABLE Training(
    T_id INT PRIMARY KEY,
    Tcompany_name VARCHAR(20),
    T_fee INT,
    T_year INT
);

CREATE TABLE Student(
    S_id INT PRIMARY KEY,
    Drive_id INT,
    T_id INT,
    S_name VARCHAR(20),
    CGPA FLOAT,
    S_branch VARCHAR(20),
    S_dob DATE,
    FOREIGN KEY(Drive_id) REFERENCES PlacemetDrive(Drive_id) ON DELETE CASCADE,
    FOREIGN KEY(T_id) REFERENCES Training(T_id) ON DELETE CASCADE
);