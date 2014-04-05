#*****************************************************
# MySQL database schema for ChurchLogger             *  
#													 *
# Prepared by Josh Klingler							 *
#*****************************************************

USE ChurchLogger;

DROP TABLE IF EXISTS members, attendance, address;

CREATE TABLE members (
	id			int	PRIMARY KEY NOT NULL AUTO_INCREMENT, # Generated ID for members
	fname		varchar(30) NOT NULL,
	lname		varchar(30) NOT NULL
);

CREATE TABLE info (
	id 			int PRIMARY KEY NOT NULL REFERENCES members.id,
	phone		varchar(17), 
	email		varchar(50),
	join_date	date,
	note		varchar(100)	
);

CREATE TABLE member_address (
	id			int PRIMARY KEY NOT NULL REFERENCES members.id,
	straddress	varchar(50),
	state		char(2),  		# 2 letter state
	zip			varchar(5)		
);

CREATE TABLE church (
	id 			int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name		varchar(40)
);

CREATE TABLE church_address (
	id			int PRIMARY KEY NOT NULL REFERENCES members.id,
	straddress	varchar(50),
	state		char(2),  		# 2 letter state
	zip			varchar(5)		
);

CREATE TABLE church_user (
	user 		varchar(20),
	password	varchar(15)
);


CREATE TABLE attendance (
	id 				int,
	service_date	date,
	PRIMARY KEY (id, service_date)
);