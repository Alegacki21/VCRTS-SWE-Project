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

CREATE TABLE CloudController (
    username VARCHAR(50) NOT NULL,          
    password VARCHAR(100) NOT NULL,         
    PRIMARY KEY (username)
);

INSERT INTO CloudController (username, password)
VALUES ('admin', 'admin123');


CREATE TABLE Vehicle (
    VIN VARCHAR(17) NOT NULL,
    ownerID INT(10) NOT NULL,
    residencyTime TIME NOT NULL,
    compPower INT NOT NULL, 
    notes TEXT,
    PRIMARY KEY (VIN),
    FOREIGN KEY (ownerID) REFERENCES VehicleOwner(ownerID)
);

CREATE TABLE Job (
    jobID INT (10) NOT NULL,
    clientID INT(10) NOT NULL,
    jobDuration TIME NOT NULL,
    jobDeadline DATE NOT NULL,
    purpose TEXT NOT NULL,
    PRIMARY KEY (jobID),
    FOREIGN KEY (clientID) REFERENCES JobSubmitter(clientID)
);

