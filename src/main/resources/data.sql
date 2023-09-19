insert into user_details(id,birth_date,name)
values(10001, current_date(), 'Gaurav');

insert into user_details(id,birth_date,name)
values(10002, current_date(), 'Pratap');

insert into user_details(id,birth_date,name)
values(10003, current_date(), 'Singh');

insert into post(id,description,user_id)
values(20001,'This is my Social Media API', 10001);

insert into post(id,description,user_id)
values(20002,'Creating demo post for Gaurav', 10001);

insert into post(id,description,user_id)
values(20003,'Demo post for Pratap', 10002);

insert into post(id,description,user_id)
values(20004,'This is a Example of REST API created by Gaurav', 10002);