create table type(
   id serial primary key,
   name varchar(255) 
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price int
);

insert into type(name) values ('СЫР'), ('неСЫР'), ('МОЛОКО');

insert into product(name, type_id, expired_date, price)
 values ('мороженое', 2, date '14/02/2022', 150),
  ('мороженое мясо', 2, date '14/05/2022', 650),
  ('Сыр плавленный', 1, date '14/06/2022', 100),
  ('Сыр моцарелла', 1, date '14/08/2022', 350),
  ('молоко', 3, date '14/04/2022, 120');
 
select p.name, t.name from product as p
 join type as t on t.id = p.type_id
 where t.name = ('СЫР');
 
select name from product
 where name like '%мороженое%';
 
select name from product
 where expired_date < current_date;
 
select name from product
 where price =
 (select max(price) from product);
 
select t.name, count(p.type_id) from type as t
 join product as p
 on p.type_id = t.id
group by t.name;

select p.name, t.name from product as p
 join type as t on t.id = p.type_id
 where t.name = ('СЫР') or t.name = ('МОЛОКО');
 
select t.name, count(p.type_id) from type as t
 join product as p
 on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

select *, t.name from product as p
 join type as t
 on t.id = p.type_id;
