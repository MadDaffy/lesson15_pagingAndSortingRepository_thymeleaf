CREATE TABLE products (id serial, title varchar(100), price int);

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
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users
VALUES
('user1', '{noop}123', true),
('user2', '{noop}123', true);

CREATE TABLE authorities (
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk_1
    FOREIGN KEY (username)
    REFERENCES users (username)
);

INSERT INTO authorities
VALUES
('user1', 'ROLE_ADMIN'),
('user1', 'ROLE_USER'),
('user2', 'ROLE_USER');