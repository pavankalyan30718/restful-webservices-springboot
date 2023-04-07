insert into user_details(id,birthdate,name)
values(1001,current_date(),'PK');

insert into user_details(id,birthdate,name)
values(1002,current_date(),'Poorna');

insert into user_details(id,birthdate,name)
values(1003,current_date(),'Anudeep');


insert into post(id,description,user_id)
values(2001,'I want to learn AWS',1001);

insert into post(id,description,user_id)
values(2002,'I want to get AWS Certified',1003);

insert into post(id,description,user_id)
values(2003,'I want to learn Multicloud',1003);