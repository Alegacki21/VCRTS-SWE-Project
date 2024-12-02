DROP TABLE IF EXISTS Vehicle;
DROP TABLE IF EXISTS Job;
DROP TABLE IF EXISTS VehicleOwner;
DROP TABLE IF EXISTS JobSubmitter;
DROP TABLE IF EXISTS CloudController;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS VehicleAssignment;

CREATE TABLE User (
    userID VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    userType VARCHAR(20),
    address VARCHAR(200),
    balance DECIMAL(10,2),
    phoneNumber VARCHAR(20),
    password VARCHAR(100)
);

CREATE TABLE Vehicle (
    VIN VARCHAR(50) PRIMARY KEY,
    ownerID VARCHAR(50),
    make VARCHAR(50),
    model VARCHAR(50),
    year INT,
    compPower DECIMAL(10,2),
    storageCapacity DECIMAL(10,2),
    status VARCHAR(20),
    FOREIGN KEY (ownerID) REFERENCES User(userID)
);

CREATE TABLE Job (
    jobID VARCHAR(50) PRIMARY KEY,
    clientID VARCHAR(50),
    jobDuration INT,
    jobDeadline DATE,
    purpose VARCHAR(200),
    status VARCHAR(20),
    submissionTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estimatedCompletionTime INT,
    FOREIGN KEY (clientID) REFERENCES User(userID)
);

CREATE TABLE VehicleAssignment (
    jobID VARCHAR(50),
    VIN VARCHAR(50),
    assignmentTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (jobID, VIN),
    FOREIGN KEY (jobID) REFERENCES Job(jobID),
    FOREIGN KEY (VIN) REFERENCES Vehicle(VIN)
);

