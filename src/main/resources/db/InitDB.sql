DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS voting;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS global_seqMenu;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE SEQUENCE global_seqMenu START WITH 10001;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                           NOT NULL,
    email      VARCHAR UNIQUE                    NOT NULL,
    password   VARCHAR                           NOT NULL,
    registered TIMESTAMP           DEFAULT now() NOT NULL,
    enabled    BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name          TEXT NOT NULL,
    numberOfVotes INT  NOT NULL
);

CREATE TABLE menu
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seqMenu'),
    restaurant_id INTEGER NOT NULL,
    name          TEXT    NOT NULL,
    price         INT     NOT NULL,
    date_menu     TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE voting
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER   NOT NULL,
    user_id       INTEGER   NOT NULL,
    registered    TIMESTAMP NOT NULL
);