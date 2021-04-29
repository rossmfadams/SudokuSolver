drop table user;
CREATE TABLE user
	(
	username	varchar (25),
	password	binary(16));

alter table user 
	add constraint user_username_pk primary key(username); 

insert into user
	values('rossa', aes_encrypt('graduate','key'));
insert into user
	values('ambera', aes_encrypt('graduate1','key'));
insert into user
	values('ianm', aes_encrypt('graduate2','key'));
insert into user
	values('sandersonh', aes_encrypt('graduate3','key'));
