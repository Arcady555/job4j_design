insert into roles(role) values('master');
insert into roles(role) values('slave');
insert into roles(role) values('guest');

insert into users(name, role_id) values('Arcady', 1);

insert into rules(rule) values('do not touch everything!');
insert into rules(rule) values('do what you wont!');
insert into rules(rule) values('obey everybody!');

insert into role_rules(role_id, rule_id) values(1, 2);
insert into role_rules(role_id, rule_id) values(2, 3);
insert into role_rules(role_id, rule_id) values(3, 1);

insert into categories(category) values('offer buy');
insert into categories(category) values('offer sale');

insert into states(state) values('accept');
insert into states(state) values('done');
insert into states(state) values('reject');
insert into states(state) values('perform');

insert into item(app, user_id, category_id, state_id) values('Давайте начнём!', 1, 1, 4);

insert into comments(comment, item_id) values('А как же!', 1);

insert into attachs(attach, item_id) values('/home/arcady1/Рабочий стол/DB/23apr2022/файл.txt', 1);
