create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price)
 values ('Samsung', 60000), ('Apple', 70000),
 ('Sony', 65000), ('RobotFedor', 100000);
 
insert into people(name)
 values ('Arcady'), ('Masha'), ('Sasha'),
 ('Lesha'), ('Misha'), ('Lena');
 
insert into devices_people(people_id, device_id)
  values (1, 1), (1, 3), (1, 4),
  (2, 2),
  (3, 2), (3, 4),
  (4, 1), (4, 2), (4, 3), (4, 4),
  (5, 2), (5, 3),
  (6, 1);
  
select avg(price) from devices;
  
select p.name, avg(d.price) from people as p
 join devices_people as dp
  on dp.people_id = p.id
 join devices as d
  on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) from people as p
 join devices_people as dp
  on dp.people_id = p.id
 join devices as d
  on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
