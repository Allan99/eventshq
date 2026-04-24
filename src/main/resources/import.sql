insert into tb_category (description) values ('Curso');
insert into tb_category (description) values ('Oficina');

insert into tb_attendee (name, email) values ('José Silva', 'Jose@gmail.com');
insert into tb_attendee (name, email) values ('Tiago Faria', 'Tiago@gmail.com');
insert into tb_attendee (name, email) values ('Maria do Rosário', 'Maria@gmail.com');
insert into tb_attendee (name, email) values ('Teresa Silva', 'Teresa@gmail.com');

insert into tb_activity (description, name, price, category_id) values ('Aprenda HTML de forma prática', 'Curso de HTML', 80.00, 1);
insert into tb_activity (description, name, price, category_id) values ('Controle versões dos seus projetos', 'Oficina de GitHub', 50.00, 2);

insert into tb_block (start, finish, activity_id) values ('2017-09-25 08:00:00', '2017-09-25 11:00:00', 1);
insert into tb_block (start, finish, activity_id) values ('2017-09-25 14:00:00', '2017-09-25 18:00:00', 2);
insert into tb_block (start, finish, activity_id) values ('2017-09-26 08:00:00', '2017-09-26 11:00:00', 2);

insert into attendee_activity (attendee_id, activity_id) values (1, 1);
insert into attendee_activity (attendee_id, activity_id) values (1, 2);
insert into attendee_activity (attendee_id, activity_id) values (2, 1);
insert into attendee_activity (attendee_id, activity_id) values (3, 1);
insert into attendee_activity (attendee_id, activity_id) values (3, 2);
insert into attendee_activity (attendee_id, activity_id) values (4, 2);