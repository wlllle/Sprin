CREATE DATABASE IF NOT EXISTS Firm CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE Firm;

CREATE TABLE IF NOT EXISTS Position
(
	ID int NOT NULL PRIMARY KEY,
	Name varchar(100) NOT NULL,
	Duties tinytext NOT NULL
);

CREATE TABLE IF NOT EXISTS Person
(
	ID int NOT NULL PRIMARY KEY,
	FirstName varchar(100) NOT NULL,
	MiddleName varchar(100) NOT NULL,
	LastName varchar(100) NOT NULL,
	HomeAdress varchar(100) NOT NULL,
	Education varchar(100) NOT NULL,
	RecruitmentDate date NOT NULL
);

CREATE TABLE IF NOT EXISTS Subdivision
(
	ID int NOT NULL PRIMARY KEY,
	Name varchar(100) NOT NULL,
	HeadPersonID int NOT NULL
		REFERENCES Person(ID),
	HeadDivisionID int
		REFERENCES Subdivision(ID)
);

CREATE TABLE IF NOT EXISTS SubdivisionsPositions
(
	ID int NOT NULL PRIMARY KEY,
	SubdivisionID int NOT NULL
		REFERENCES Firm.Subdivision(ID),
	PositionID int NOT NULL
		REFERENCES Firm.Position(ID),
	NumberOfPositions int NOT NULL
);

CREATE TABLE IF NOT EXISTS PositionsHistory
(
	ID int NOT NULL PRIMARY KEY,
	PersonID int NOT NULL
		REFERENCES Person(ID),
	SubdivisionsPositionsID int NOT NULL
		REFERENCES SubdivisionsPositions(ID),
	ApprointmentDate date NOT NULL,
	OusterDate date
);
