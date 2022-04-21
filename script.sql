create table men(
	id serial primary key,
	name varchar(255),
	sex boolean,
	age smallint
);

insert into men(name, sex, age) values ('Аркаша', true, 49);

select * from men;

update men set name = 'Аркадий';

select * from men;

delete from men;

select * from men;
