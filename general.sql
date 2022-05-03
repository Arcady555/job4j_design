create table orientations(
	id serial primary key,
	orientation varchar(255)
);

create table lovers(
	id serial primary key,
	sex boolean,
	name varchar(255),
	position_id int references orientations(id)
);

insert into orientations(orientation) values ('getero');
insert into orientations(orientation) values ('homo');
insert into orientations(orientation) values ('bi');

insert into lovers(sex, name, position_id) values (true, 'Arcasha', 1);
insert into lovers(sex, name, position_id) values (false, 'Masha', 3);
insert into lovers(sex, name, position_id) values (true, 'Elton John', 2);

select * from lovers;
select * from orientations where id in (select id from lovers);






create table banks(
	id serial primary key,
	name varchar(255)
);

create table payment_systems(
	id serial primary key,
	name varchar(255)
);

create table banks_payments(
	id serial primary key,
	banks_id int references banks(id),
	payments_id int references payment_systems(id)
);

insert into banks(name) values('SberBank');
insert into banks(name) values('AlfaBank');
insert into banks(name) values('VTB');

insert into payment_systems(name) values('VISA');
insert into payment_systems(name) values('MasterCard');
insert into payment_systems(name) values('Mir');
insert into payment_systems(name) values('American Express');

insert into banks_payments(banks_id, payments_id) values(1, 1);
insert into banks_payments(banks_id, payments_id) values(1, 2);
insert into banks_payments(banks_id, payments_id) values(1, 3);
insert into banks_payments(banks_id, payments_id) values(1, 4);
insert into banks_payments(banks_id, payments_id) values(2, 1);
insert into banks_payments(banks_id, payments_id) values(2, 2);
insert into banks_payments(banks_id, payments_id) values(3, 3);
insert into banks_payments(banks_id, payments_id) values(3, 4);

select * from banks;
select * from payment_systems;
select * from banks_payments where id in(select id from banks);
select * from banks_payments where id in(select id from payment_systems);






create table husbands(
	id serial primary key,
	name varchar(255)
);

create table wifes(
	id serial primary key,
	name varchar(255)
);

create table families(
	id serial primary key,
	wife_id int references wifes(id) unique,
	husband_id int references husbands(id) unique
);

insert into husbands(name) values('Arcady');
insert into husbands(name) values('Alexandr');
insert into husbands(name) values('Alexey');

insert into wifes(name) values('Lena');
insert into wifes(name) values('Masha');
insert into wifes(name) values('Elya');

insert into families(wife_id, husband_id) values(2, 1);
insert into families(wife_id, husband_id) values(1, 3);
insert into families(wife_id, husband_id) values(3, 2);

select * from husbands;
