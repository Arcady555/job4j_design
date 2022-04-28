create table orientations(
	id serial primary key,
	orientation varchar(255)
);
create table lovers(
	id serial primary key,
	sex boolean,
	name varchar(255),
	height smallint,
	weight smallint,
	orientation_id int references orientations(id)
);
insert into orientations(orientation) values ('getero');
insert into orientations(orientation) values ('homo');
insert into orientations(orientation) values ('bi');
insert into lovers(sex, name, height, weight, orientation_id) values (true, 'Arcasha', 172, 80, 1);
insert into lovers(sex, name, height, weight, orientation_id) values (false, 'Masha', 175, 70, 3);
insert into lovers(sex, name, height, weight, orientation_id) values (true, 'Elton John', 160, 90, 2);

select * from lovers join orientations on lovers.orientation_id = orientations.id;
select * from orientations join lovers on lovers.orientation_id = orientations.id;
select l.name as  Имя, l.sex as Пол, o.orientation as Ориентация from lovers as l join orientations as o on l.orientation_id = o.id;
select o.orientation as Ориентация, l.name as Имя, l.height as Рост, l.weight as Вес from orientations as o join lovers as l on l.orientation_id = o.id;
