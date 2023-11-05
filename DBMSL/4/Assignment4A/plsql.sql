-- Write a PL/SQL code block to calculate the area 
-- of a circle for a value of radius varying from 5 to 
-- 9. Store the radius and the corresponding values 
-- of calculated area in an empty table named 
-- areas,  consisting  of  two  columns,  radius  and  area


CREATE TABLE areas(
    radius INT,
    area FLOAT
);


-- Creating PROCEDURE
DELIMITER $$
CREATE PROCEDURE Find_area()
BEGIN
    -- Declare variables for radius and area
    DECLARE radius_value INT;
    DECLARE area_value FLOAT;

    -- Set initial radius value
    SET radius_value = 5;

    -- Start of While loop 
    WHILE radius_value <= 9 DO

        SET area_value = 3.14 * radius_value * radius_value;

        INSERT INTO areas (radius,area) VALUES (radius_value, area_value);

        SET radius_value = radius_value + 1;

    END WHILE;
END $$
DELIMITER ;

-- Calling PROCEDURE
CALL Find_area();
