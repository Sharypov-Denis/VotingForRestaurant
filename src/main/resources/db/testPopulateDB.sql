DELETE
FROM voting;
DELETE
FROM menu;
DELETE
FROM restaurant;
DELETE
FROM user_roles;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurant (id, user_id, name, numberOfVotes)
VALUES (100001, 100000, 'Русский ресторан', 0),
       (100002, 100000, 'Индийский ресторан', 0),
       (100003, 100000, 'Китайский ресторан', 0),
       (100004, 100000, 'Европейский ресторан', 0),
       (100005, 100000, 'Японский ресторан', 0);

INSERT INTO menu (restaurant_id, name, price)
VALUES (100001, 'Картофельное пюре', 50),
       (100001, 'Куриная котлета', 80),
       (100001, 'Компот', 20),
       (100002, 'Курица карри', 71),
       (100002, 'Пропаренный рис', 39),
       (100002, 'Масала', 27),
       (100003, 'Утка по пекински', 120),
       (100003, 'Жареный ананас', 101),
       (100003, 'Китайский чай', 43),
       (100004, 'Стей Канзас', 270),
       (100004, 'Картофель фри', 56),
       (100004, 'Безалкогольное пиво', 39),
       (100005, 'Филадельфия', 260),
       (100005, 'Вок из говядины', 200),
       (100005, 'Японский чай', 50);


INSERT INTO voting (restaurant_id, user_id, registered)
VALUES (100001, 100000, '2020-01-30 10:01:00'),
       (100002, 100000, '2020-01-30 10:02:00'),
       (100003, 100001, '2020-01-30 10:03:00');