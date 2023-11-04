-- A view is a virtual table based on the result of a SELECT query. It allows you to store a complex query as a table-like object that can be queried, updated, or used in other views.

CREATE VIEW StudentDetails AS
SELECT 
    S.S_id,
    S.S_name,
    S.CGPA,
    S.S_branch,
    PD.Pcompany_name AS Placement_Company,
    PD.Package AS Placement_Package,
    PD.Location AS Placement_Location,
    T.Tcompany_name AS Training_Company,
    T.T_fee AS Training_Fee,
    T.T_year AS Training_Year
FROM 
    Student S
JOIN 
    PlacemetDrive PD ON S.Drive_id = PD.Drive_id
JOIN 
    Training T ON S.T_id = T.T_id;
