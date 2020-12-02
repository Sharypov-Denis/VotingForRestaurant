DELETE FROM voting;
DELETE FROM menu;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurant (id, user_id, name, numberOfVotes)
VALUES (100001, 100000, 'RESTAURANT1', 0),
       (100002, 100000, 'RESTAURANT2', 0),
       (100003, 100001, 'RESTAURANT3', 0);

INSERT INTO menu (restaurant_id, name, price)
VALUES (100001, 'KAKA1', 1001),
       (100002, 'KAKA2', 1002),
       (100002, 'KAKA2', 2002),
       (100002, 'KAKA22', 2022),
       (100003, 'KAKA3', 3003);


INSERT INTO voting (restaurant_id, user_id, registered)
VALUES (100001, 100000, '2020-01-30 10:01:00'),
       (100002, 100000, '2020-01-30 10:02:00'),
       (100003, 100001, '2020-01-30 10:03:00');