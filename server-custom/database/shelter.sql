-- database m2_final_project
BEGIN TRANSACTION;

-- Drop all db objects in the proper order
DROP TABLE IF EXISTS Pets;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS Volunteer;
DROP TABLE IF EXISTS users;

-- Create the tables and constraints
CREATE TABLE users (
                       user_id SERIAL UNIQUE,
                       PRIMARY KEY (user_id),
                       username varchar(50) NOT NULL UNIQUE,
                       password_hash varchar(200) NOT NULL,
                       role varchar(50) NOT NULL
);

CREATE TABLE Volunteer (
                           VolNumber SERIAL UNIQUE,
                           PRIMARY KEY (VolNumber),
                           user_id INT,
                           app_status VARCHAR(15),
                           FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE owners (
                        owner_id SERIAL UNIQUE,
                        PRIMARY KEY (owner_id),
                        user_id INT,
                        first_name VARCHAR(50),
                        last_name VARCHAR(50),
                        Address VARCHAR(100),
                        phone VARCHAR(15),
                        Volunteer BOOLEAN,
                        FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE Pets (
                      AnimalId SERIAL UNIQUE,
                      PRIMARY KEY (AnimalId),
                      PetName VARCHAR(50),
                      Breed VARCHAR(25),
                      Color VARCHAR(50),
                      Age INT,
                      Photo VARCHAR(255),
                      Species VARCHAR(25),
                      active BOOLEAN,
                      owner_id INT,
                      active_date TIMESTAMP DEFAULT(CURRENT_TIMESTAMP),
                      adoption_date DATE,
                      FOREIGN KEY (owner_id) REFERENCES owners (owner_id)
);

-- Insert some sample starting data

-- Users
-- Password for all users is password
INSERT INTO users (username, password_hash, role)
VALUES
    ('user1', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('user2', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('user3', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('user4', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin1', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

-- Insert test data into Volunteer table
INSERT INTO Volunteer (user_id, app_status)
VALUES
    (1, 'Approved'),
    (2, 'Pending'),
    (3, 'Approved'),
    (4, 'Rejected');

-- Insert test data into owners table
INSERT INTO owners (user_id, first_name, last_name, Address, phone, Volunteer)
VALUES
    (1, 'John', 'Doe', '123 Maple Street', '5551234', TRUE),
    (2, 'Jane', 'Smith', '456 Oak Avenue', '5555678', FALSE),
    (3, 'Alice', 'Jones', '789 Pine Road', '5559012', TRUE),
    (4, 'Bob', 'Brown', '321 Birch Boulevard', '5553456', FALSE);

-- Insert test data into Pets table
INSERT INTO Pets (PetName, Breed, Color, Age, Photo, Species, active, owner_id, active_date, adoption_date)
VALUES
    ('Spot', 'Labrador', 'Black', 3, 'labrador_black.jpg', 'Dog', TRUE, 1, '2022-01-15', NULL),
    ('Fluffy', 'Persian', 'White', 2, 'persian_white.jpg', 'Cat', TRUE, 2, '2022-02-20', NULL),
    ('Trapper', 'Golden Retriever', 'Golden', 5, 'golden_retriever.jpg', 'Dog', TRUE, 3, '2022-03-10', NULL),
    ('Blue Tick', 'Siamese', 'Brown', 1, 'siamese_brown.jpg', 'Cat', FALSE, 4, '2022-04-05', '2023-01-12'),
    ('Frankie', 'Maine Coon', 'White', 1, 'frankie.jpg', 'Cat', FALSE, 4, '2021-08-03', NULL);

COMMIT TRANSACTION;
