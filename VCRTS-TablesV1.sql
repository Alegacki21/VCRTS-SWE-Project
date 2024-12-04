DROP TABLE IF EXISTS Vehicle;
DROP TABLE IF EXISTS Job;
DROP TABLE IF EXISTS VehicleOwner;
DROP TABLE IF EXISTS JobSubmitter;
DROP TABLE IF EXISTS CloudController;

CREATE TABLE VehicleOwner (
    ownerID INT (10) NOT NULL,           
    fullName VARCHAR(100) NOT NULL,         
    email VARCHAR(100) NOT NULL UNIQUE,     
    password VARCHAR(100) NOT NULL,         
    address VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,                
    PRIMARY KEY (ownerID)
);
-- Insert into VehicleOwner table
INSERT INTO vehicleowner (ownerID, fullName, email, password, address, state, country, phoneNumber) 
VALUES (123, 'Lee Everett', 'Lee@gmail.com', 'password123', '123 main street', 'New York', 'USA', '555-123-0912');

INSERT INTO vehicleowner (ownerID, fullName, email, password, address, state, country, phoneNumber) 
VALUES (456, 'Batman Doe', 'batdoe@yahoo.com', 'Riddle me this', '456 Gotham Drive', 'California', 'USA', '125-436-7890');

INSERT INTO vehicleowner (ownerID, fullName, email, password, address, state, country, phoneNumber) 
VALUES (789, 'Will Smith', 'WillSmith@gmail.com', 'password78119', '789 Oak Avenue', 'New York', 'USA', '055-729-2223');


CREATE TABLE JobSubmitter (
    clientID INT(10) NOT NULL,          
    fullName VARCHAR(100) NOT NULL,         
    email VARCHAR(100) NOT NULL UNIQUE,     
    password VARCHAR(100) NOT NULL,         
    subPlan VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,           
    PRIMARY KEY (clientID)
);

INSERT INTO JobSubmitter (clientID, fullName, email, password, subPlan, address, state, country, phoneNumber) 
VALUES (123, 'Carl Johnson', 'CJ@gmail.com', 'password123', 'Monthly', '123 Grove street', 'San Andreas', 'USA', '345-123-0112');

INSERT INTO JobSubmitter (clientID, fullName, email, password, subPlan, address, state, country, phoneNumber) 
VALUES (456, 'Spider Man', 'PeterParker@gmail.com', 'spider516', 'Forever', '123 Street', 'New York', 'USA', '345-223-1912');

INSERT INTO JobSubmitter (clientID, fullName, email, password, subPlan, address, state, country, phoneNumber) 
VALUES (789, 'Santa Claus', 'SantaClaus1@gmail.com', 'Santa baby', 'Yearly', 'North Pole', 'Antartica', 'Antartica', '115-193-9912');



CREATE TABLE CloudController (
    username VARCHAR(50) NOT NULL,          
    password VARCHAR(100) NOT NULL,         
    PRIMARY KEY (username)
);

INSERT INTO CloudController (username, password)
VALUES ('admin', 'admin123');


CREATE TABLE Vehicle (
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ownerID INT(10) NOT NULL,
    VIN VARCHAR(17) NOT NULL,
    residencyTime TIME NOT NULL,
    compPower INT NOT NULL, 
    notes TEXT,
    PRIMARY KEY (VIN),
    FOREIGN KEY (ownerID) REFERENCES VehicleOwner(ownerID)
);

CREATE TABLE Job (
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    jobID INT (10) NOT NULL AUTO_INCREMENT,
    clientID INT(10) NOT NULL,
    subscriptionPlan VARCHAR(50),
    jobDuration TIME NOT NULL,
    jobDeadline DATE NOT NULL,
    purpose TEXT NOT NULL,
    PRIMARY KEY (jobID),
    FOREIGN KEY (clientID) REFERENCES JobSubmitter(clientID)
);

