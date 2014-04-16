--#*******************************************************
--# This file contains example queries for ChurchLogger. *  
--#													   *
--# Prepared by Josh Klingler							   *
--#*******************************************************

--# Selects data to populate table in GUI
SELECT fname, lname, phone, join_date, birth_date, email, straddress, zip
FROM members NATURAL JOIN info NATURAL JOIN member_address
WHERE cID = #INSERT CHURCH ID HERE

--# Takes login info and checks if username/pass is in the table. If it is valid, 
--# the church ID is returned. If it isn't, it is an empty set.
SELECT cID
	FROM church_user
	WHERE user = (userInput) AND password = (userInput);

# Returns table of all members that have not gone to church since a certain day.
SELECT fname, lname, phone, email
	FROM members NATURAL JOIN info
	WHERE members.mID NOT IN (SELECT mID
					FROM attendance
					WHERE service_date <= (USER INPUT) );

--# Inserts a new member
BEGIN
	INSERT INTO members(fname, lname)
		VALUES('USER INPUT', 'USER INPUT')

	INSERT INTO info(mID, phone, email, join_date, birth_date, note)
		VALUES(LAST_USER_ID(), 'USER INPUT','USER INPUT','USER INPUT','USER INPUT','USER INPUT')

	INSERT INTO member_address(mID, straddress, state, zip)
		VALUES(LAST_USER_ID(), 'USER INPUT','USER INPUT','USER INPUT',)
COMMIT;

--# Insert new user
INSERT INTO church_user(user, password)
	VALUES('USER INPUT','USER INPUT');
