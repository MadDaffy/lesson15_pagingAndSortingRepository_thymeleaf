CREATE TABLE products (id serial, title varchar(100), price int, view int DEFAULT (0));

INSERT INTO products (title, price)
VALUES
    ('Product1', 10),
    ('Product2', 20),
    ('Product3', 30),
    ('Product4', 40),
    ('Product5', 50),
    ('Product6', 60),
    ('Product7', 70),
    ('Product8', 80),
    ('Product9', 90),
    ('Product10', 100),
    ('Product11', 110),
    ('Product12', 120),
    ('Product13', 130),
    ('Product14', 140),
    ('Product15', 150),
    ('Product16', 160),
    ('Product17', 170),
    ('Product18', 180),
    ('Product19', 190),
    ('Product20', 200);

CREATE TABLE users (
    id serial,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,

--     enabled boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role (
    id serial,
    name VARCHAR(50) NOT NULL,
--     enabled boolean NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE user_role (
    user_id int NOT NULL,
    role_id int NOT NULL,
--     enabled boolean NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
                       REFERENCES users (id)
                       ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
                       REFERENCES role(id)
                       ON DELETE NO ACTION ON UPDATE NO ACTION

);

INSERT INTO role (name)
VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO users (username, password, name, email)
VALUES
('admin', '$2a$10$6BYYRmfz/.fWNh9.WgeppeOpBKHUOHw2spboXnzUlfxNMgH5U2lx2', 'Dmitrii Sys', 'kuzutaka@yandex.ru');

insert into user_role (user_id, role_id)
VALUES
(1, 1),
(1, 2);

-- CREATE TABLE authorities (
--     username varchar(50) NOT NULL,
--     authority varchar(50) NOT NULL,
--
--     CONSTRAINT authorities_idx UNIQUE (username, authority),
--
--     CONSTRAINT authorities_ibfk_1
--     FOREIGN KEY (username)
--     REFERENCES user (username)
-- );
--
-- INSERT INTO authorities
-- VALUES
-- ('user1', 'ROLE_ADMIN'),
-- ('user1', 'ROLE_USER'),
-- ('user2', 'ROLE_USER');