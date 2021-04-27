drop table User;
Create TABLE User
	(
	username	varchar (25),
	password	binary(16));

alter table User 
	add constraint User_username_pk primary key(username); 
ALTER TABLE User
	ADD CONSTRAINT User_password_CK CHECK (DATALENGTH (password) >= 6);
