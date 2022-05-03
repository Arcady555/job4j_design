create table roles(
	id serial primary key,
	role varchar(255)
);

create table users(
	id serial primary key,
	name varchar(255),
	role_id int references roles(id)
);

create table rules(
	id serial primary key,
	rule text
);

create table role_rules(
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);

create table categories(
	id serial primary key,
	category varchar(255)
);

create table states(
	id serial primary key,
	state varchar(255)
);

create table item(
	id serial primary key,
	app text,
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

create table comments(
	id serial primary key,
	comment text,
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	attach text,
	item_id int references item(id)
);
