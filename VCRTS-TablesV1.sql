DROP TABLE IF EXISTS Vehicle;
DROP TABLE IF EXISTS Job;
DROP TABLE IF EXISTS VehicleOwner;
DROP TABLE IF EXISTS JobSubmitter;
DROP TABLE IF EXISTS CloudController;

CREATE TABLE VehicleOwner (
    userID VARCHAR(50) NOT NULL,           
    email VARCHAR(100) NOT NULL UNIQUE,     
    username VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,         
    password VARCHAR(100) NOT NULL,         
    balance DOUBLE NOT NULL,
    paymentMethod VARCHAR(50) NOT NULL,
    paymentAccount VARCHAR(50) NOT NULL,
    PRIMARY KEY (ownerID)
);

CREATE TABLE JobSubmitter (
    userID VARCHAR(50) NOT NULL,          
    email VARCHAR(100) NOT NULL UNIQUE,     
    username VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,         
    password VARCHAR(100) NOT NULL,         
    balance DOUBLE NOT NULL,
    paymentMethod VARCHAR(50) NOT NULL,
    subscriptionPlan VARCHAR(20) NOT NULL,
    paymentAccount VARCHAR(50) NOT NULL,
    PRIMARY KEY (clientID)
);

CREATE TABLE Vehicle (
    VIN VARCHAR(17) NOT NULL,
    userID VARCHAR(50) NOT NULL,
    model VARCHAR(100) NOT NULL,
    make VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    computationalPower DOUBLE NOT NULL,
    storageCapacity DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL,
    location VARCHAR(100),
    arrivalTime TIME,
    departureTime TIME,
    PRIMARY KEY (VIN),
    FOREIGN KEY (ownerID) REFERENCES VehicleOwner(ownerID)
);

CREATE TABLE Job (
    jobID VARCHAR(50) NOT NULL,
    userID VARCHAR(50) NOT NULL,
    jobDuration INT NOT NULL,
    jobDeadline DATE NOT NULL,
    purpose TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    PRIMARY KEY (jobID),
    FOREIGN KEY (clientID) REFERENCES JobSubmitter(clientID)
);

CREATE TABLE CloudController (
    username VARCHAR(50) NOT NULL,          
    password VARCHAR(100) NOT NULL,         
    PRIMARY KEY (username)
);

INSERT INTO CloudController (username, password)
VALUES ('admin', 'admin123');