DELETE FROM voting;
DELETE FROM menu;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM user_roles;

alter sequence global_seq RESTART with 100000;
alter sequence global_seqMenu RESTART with 10001;


insert into users (name, email, password)
values ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('UserMax', 'usermax@gmail.com', '12345'),
       ('UserIgor', 'userigor@gmail.com', '12345'),
       ('UserPetr', 'userpetr@gmail.com', '12345');

insert into user_roles (role, user_id)
values ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100002),
       ('USER', 100003),
       ('USER', 100004);

insert into restaurant (id, name, numberOfVotes)
values (100000, 'Русский ресторан', 0),
       (100001, 'Индийский ресторан', 0),
       (100002, 'Китайский ресторан', 0),
       (100003, 'Европейский ресторан', 0),
       (100004, 'Японский ресторан', 0);

insert into menu (id, restaurant_id, name, price, date_menu)
values (10001, 100000, 'Картофельное пюре', 50, '2021-01-12 00:00:00'),
       (10002, 100000, 'Куриная котлета', 80,'2021-01-12 00:00:00'),
       (10003, 100000, 'Компот', 20,'2021-01-12 00:00:00'),
       (10004, 100001, 'Курица карри', 71,'2021-01-12 00:00:00'),
       (10005, 100001, 'Пропаренный рис', 39,'2021-01-12 00:00:00'),
       (10006, 100001, 'Масала', 27,'2021-01-12 00:00:00'),
       (10007, 100002, 'Утка по пекински', 120,'2021-01-12 00:00:00'),
       (10008, 100002, 'Жареный ананас', 101,'2021-01-12 00:00:00'),
       (10009, 100002, 'Китайский чай', 43,'2021-01-12 00:00:00'),
       (10010, 100003, 'Стейк Канзас', 270,'2021-01-12 00:00:00'),
       (10011, 100003, 'Картофель фри', 56,'2021-01-12 00:00:00'),
       (10012, 100003, 'Безалкогольное пиво', 39,'2021-01-12 00:00:00'),
       (10013, 100004, 'Филадельфия', 260,'2021-01-12 00:00:00'),
       (10014, 100004, 'Вок из говядины', 200,'2021-01-12 00:00:00'),
       (10015, 100004, 'Японский чай', 50,'2021-01-12 00:00:00');


