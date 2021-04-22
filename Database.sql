drop table User;
Create TABLE User
	(
	username	varchar (25),
	password	binary(16));

adalter table User 
	add constraint User_username_pk primary key(username); 
ALTER TABL User
	ADD CONSTRAINT User_passord_CK CHECK (DATALENGTH(password) >= 6);
