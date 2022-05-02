create table body(
	id serial primary key,
	numberVIN text
);

create table engine(
	id serial primary key,
	numberVIN text
);

create table transmission(
	id serial primary key,
	numberVIN text
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(numberVIN) values ('0987r73626teR'),
 ('0987r73626teR'), ('0987r73626teR'), ('0987r73626teR');
 
insert into engine(numberVIN) values ('sgfasgh5564sdvsj'),
 ('m347358vhsdfh78'), ('x3631274shfr67723'), ('cb98743356743dgp9');
 
insert into transmission(numberVIN) values ('dgd5262545sjf8'),
 ('zxadre43578656'), ('fh29908765ds0'), ('lkefioqqhh9877');
 
insert into car(name, body_id, engine_id, transmission_id)
 values ('Lada', 2, 1, 4), ('ZAZ', 1, 2, null);
 
insert into car(name, body_id) values ('HZ', 4);

select c.name as Машина, b.numberVIN as Кузов,
 e.numberVIN as Двигатель, t.numberVIN as КоробкаПередач
 from car as c
 left join body as b on b.id = c.body_id
 left join engine as e on e.id = c.engine_id 
 left join transmission as t on t.id = c.transmission_id;
 
select b.numberVIN as Кузов from body as b
left join car as c on c.body_id = b.id
where c.name is null;

select e.numberVIN as Двигатель from engine as e
left join car as c on c.engine_id = e.id
where c.name is null;

select t.numberVIN as КоробкаПередач from transmission as t
left join car as c on c.engine_id = t.id
where c.name is null;
