create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
);

insert into departments(name) values ('secretary'),
 ('accounters'), ('sale'), ('logistic'), ('security');
 
insert into employees(name, departments_id) values
 ('Arcady', 3), ('Masha', 1), ('Sasha', 4), ('Anna', 2),
 ('Lesha', 3), ('Pasha', 4);
 
select * from departments as d left join employees as e on
  e.departments_id = d.id;
  
select * from departments as d right join employees as e on
  e.departments_id = d.id;
  
select * from departments as d full join employees as e on
  e.departments_id = d.id;
  
select * from departments cross join employees;
  
select * from departments as d left join employees as e on
 e.departments_id = d.id
where e.name is null;

select * from departments as d left join employees as e on
  e.departments_id = d.id;

select * from employees as e right join  departments as d on
 e.departments_id = d.id;
 
create table teens(
	id serial primary key,
	name varchar(255),
	gender boolean
);

insert into teens(name, gender) values
 ('Arcasha', true), ('Masha', false), ('Sasha', true),
 ('Elya', false), ('Sveta',false), ('Oxana', false);
 
select t1.name as t1, t2.name as t2 from teens t1 cross join teens t2
where t1.gender = true and t1.gender != t2.gender;
