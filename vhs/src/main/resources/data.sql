-- Script for initial data insert for the H2 database

-- Insert User data
INSERT INTO User (user_Name, password, active, roles)
VALUES ('admin', '$2a$04$jg2WB/1zO0WNOPA0AiI15eKSlYG83jFtj.JB1Kj77s9SVeII7tBSS', true, 'ROLE_ADMIN');
INSERT INTO User (user_Name, password, active, roles)
VALUES ('user', '$2a$04$U1QGlZ7foUA0Yo9xqbmFxe2upJkyM3a1aNZj/1XBBWnWscXSKBYu2', true, 'ROLE_USER');

-- Insert Vhs data
INSERT INTO Vhs (name, year, available)
VALUES ('The Lion King', 1995, true);
INSERT INTO Vhs (name, year, available)
VALUES ('Aladdin', 1993, true);
INSERT INTO Vhs (name, year, available)
VALUES ('Titanic', 1998, true);
INSERT INTO Vhs (name, year, available)
VALUES ('Independence Day', 1996, false);
INSERT INTO Vhs (name, year, available)
VALUES ('Cinderella', 1988, true);

-- Insert Rental data
INSERT INTO Rental (user_Id, vhs_Id, rental_Start, rental_End, rental_Returned)
VALUES (2, 1, '2022-01-01', '2022-01-15', '2022-01-10');
INSERT INTO Rental (user_Id, vhs_Id, rental_Start, rental_End, rental_Returned)
VALUES (2, 3, '2022-01-10', '2022-01-25', '2022-01-20');
INSERT INTO Rental (user_Id, vhs_Id, rental_Start, rental_End)
VALUES (2, 4, '2022-01-10', '2022-01-25');