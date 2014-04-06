#*****************************************************
# MySQL database schema for ChurchLogger             *  
#													 *
# Prepared by Josh Klingler							 *
#*****************************************************

USE ChurchLogger;

DROP TABLE IF EXISTS members, attendance, info, member_address, church, church_address, church_user;

CREATE TABLE members (
	mID			int NOT NULL AUTO_INCREMENT, # Generated ID for members
	fname		varchar(30) NOT NULL,
	lname		varchar(30) NOT NULL,
	cID			int NOT NULL,
	PRIMARY KEY(mID, cID)
);

CREATE TABLE info (
	mID 		int PRIMARY KEY NOT NULL REFERENCES members.id,
	phone		varchar(17), 
	email		varchar(50),
	join_date	date,
	birth_date	date,
	note		varchar(100)	
);

CREATE TABLE member_address (
	mID			int PRIMARY KEY NOT NULL REFERENCES members.id,
	straddress	varchar(50),
	city 		varchar(30),
	state		char(2),  		# 2 letter state
	zip			varchar(5)		
);

CREATE TABLE church (
	cID			int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name		varchar(40)
);

CREATE TABLE church_address (
	cID			int PRIMARY KEY NOT NULL REFERENCES members.id,
	straddress	varchar(50),
	city		varchar(20),
	state		char(2),  		# 2 letter state
	zip			varchar(5)		
);

# Account information for churches. 
CREATE TABLE church_user (
	user 		varchar(20),
	password	varchar(15),
	cID			int,
	sub_date	date
);

CREATE TABLE attendance (
	mID 			int,
	service_date	date,
	cID				int,
	PRIMARY KEY (mID, service_date, cID)
);