DROP TABLE IF EXISTS Vehicle;
DROP TABLE IF EXISTS Job;
DROP TABLE IF EXISTS VehicleOwner;
DROP TABLE IF EXISTS JobSubmitter;
DROP TABLE IF EXISTS CloudController;

CREATE TABLE VehicleOwner (         
    registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    
    username VARCHAR(100) NOT NULL,         
    email VARCHAR(100) NOT NULL UNIQUE,     
    password VARCHAR(100) NOT NULL,         
    address VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,                
    PRIMARY KEY (username)
);
-- Insert into VehicleOwner table

INSERT INTO vehicleowner ( username, email, password, address, state, country, phoneNumber) 
VALUES ( 'owner123', '@gmail.com', 'password123', '123 main ', 'New ', 'USA', '555-123-9112');

INSERT INTO vehicleowner ( username, email, password, address, state, country, phoneNumber) 
VALUES ( 'Lee Everett', 'Lee@gmail.com', 'password123', '123 main street', 'New York', 'USA', '555-123-0912');

INSERT INTO vehicleowner ( username, email, password, address, state, country, phoneNumber) 
VALUES ('Batman Doe', 'batdoe@yahoo.com', 'Riddle me this', '456 Gotham Drive', 'California', 'USA', '125-436-7890');

INSERT INTO vehicleowner ( username, email, password, address, state, country, phoneNumber) 
VALUES ('Will Smith', 'WillSmith@gmail.com', 'password78119', '789 Oak Avenue', 'New York', 'USA', '055-729-2223');


CREATE TABLE JobSubmitter (       
    registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
    username VARCHAR(100) NOT NULL,         
    email VARCHAR(100) NOT NULL UNIQUE,     
    password VARCHAR(100) NOT NULL,         
    subPlan VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,           
    PRIMARY KEY (username)
);

INSERT INTO JobSubmitter (username, email, password, subPlan, address, state, country, phoneNumber) 
VALUES ( 'client123', 'gmail.com', 'password123', 'Monthly', '123 street', 'Andreas', 'USA', '315-123-0112');

INSERT INTO JobSubmitter (username, email, password, subPlan, address, state, country, phoneNumber) 
VALUES ( 'Carl Johnson', 'CJ@gmail.com', 'password123', 'Monthly', '123 Grove street', 'San Andreas', 'USA', '345-123-0112');

INSERT INTO JobSubmitter ( username, email, password, subPlan, address, state, country, phoneNumber) 
VALUES ('Spider Man', 'PeterParker@gmail.com', 'spider516', 'Forever', '123 Street', 'New York', 'USA', '345-223-1912');

INSERT INTO JobSubmitter (username, email, password, subPlan, address, state, country, phoneNumber) 
VALUES ('Santa Claus', '@gmail.com', 'Santa baby', 'Yearly', 'North Pole', 'Antartica', 'Antartica', '115-193-9912');



CREATE TABLE CloudController (
    username VARCHAR(50) NOT NULL,          
    password VARCHAR(100) NOT NULL,         
    PRIMARY KEY (username)
);

INSERT INTO CloudController (username, password)
VALUES ('admin', 'admin123');


CREATE TABLE Vehicle (
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    USERNAME VARCHAR (100) NOT NULL,
    ownerID VARCHAR(100) NOT NULL,
    VIN VARCHAR(17) NOT NULL,
    residencyTime TIME NOT NULL,
    compPower INT NOT NULL, 
    notes TEXT,
    PRIMARY KEY (VIN),
    FOREIGN KEY (USERNAME) REFERENCES VehicleOwner(username)
);

CREATE TABLE Job (
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    jobID INT (10) NOT NULL AUTO_INCREMENT,
    USERNAME VARCHAR (100) NOT NULL,
    clientID VARCHAR(100) NOT NULL,
    priorityLevel VARCHAR(50),
    jobDuration TIME NOT NULL,
    jobDeadline DATE NOT NULL,
    purpose TEXT NOT NULL,
    ownerID VARCHAR(100),
    vehicleVIN VARCHAR(17),
    computationPower INT, 
    PRIMARY KEY (jobID),
    FOREIGN KEY (USERNAME) REFERENCES JobSubmitter(username)
);

