CREATE TABLE Vehicle(
	ownerID VARCHAR(10) NOT NULL,
    vehicleInfo TEXT,
    residencyTime TIME,
    compPower INT(3),
    notes TEXT,
   PRIMARY KEY(ownerID)
);

CREATE TABLE Job(
	clientID varchar(10) NOT NULL,
    subPlan varchar(10),
    jobDuration time,
    jobDeadline date,
    purpose TEXT,
    PRIMARY KEY(clientID)
);

/* CREATE TABLE User(
	userName
    password
);
*/

