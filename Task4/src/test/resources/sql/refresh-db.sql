DROP TABLE IF EXISTS user_elixir;
DROP TABLE IF EXISTS user_ingredient;
DROP TABLE IF EXISTS elixir_ingredient;
DROP TABLE IF EXISTS elixirs;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS passwords;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(200) NOT NULL UNIQUE,
    email   VARCHAR(255) NOT NULL UNIQUE,
    enabled BOOLEAN      NOT NULL DEFAULT true,
    coins   INTEGER      NOT NULL DEFAULT 100
);
CREATE TABLE passwords
(
    id       BIGSERIAL PRIMARY KEY,
    user_id  INTEGER      NOT NULL REFERENCES users,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE user_role
(
    id      BIGSERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users,
    role_id INTEGER NOT NULL REFERENCES roles
);
CREATE TABLE ingredients
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    type VARCHAR(100) NOT NULL,
    cost INTEGER      NOT NULL CHECK (cost >= 0)
);
CREATE TABLE elixirs
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(100) NOT NULL UNIQUE,
    cost  INTEGER      NOT NULL CHECK (cost >= 0),
    level INTEGER      NOT NULL CHECK (level > 0)
);
CREATE TABLE elixir_ingredient
(
    elixir_id     INTEGER NOT NULL REFERENCES elixirs,
    ingredient_id INTEGER NOT NULL REFERENCES ingredients,
    PRIMARY KEY (elixir_id, ingredient_id)
);
CREATE TABLE user_ingredient
(
    user_ingredient_id BIGSERIAL PRIMARY KEY,
    user_id            INTEGER NOT NULL REFERENCES users,
    ingredient_id      INTEGER NOT NULL REFERENCES ingredients
);
CREATE TABLE user_elixir
(
    user_elixir_id BIGSERIAL PRIMARY KEY,
    user_id        INTEGER NOT NULL REFERENCES users,
    elixir_id      INTEGER NOT NULL REFERENCES elixirs
);

INSERT INTO roles(name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');
INSERT INTO ingredients(name, type, cost)
VALUES ('Iron', 'SOLID', 100),
       ('Water', 'LIQUID', 100),
       ('Blueberry', 'HERB', 100),
       ('Flour', 'POWDER', 100);
INSERT INTO elixirs(name, cost, level)
VALUES ('Speed elixir', 1000, 1),
       ('Strength elixir', 2000, 2),
       ('Regeneration elixir', 3000, 3);
INSERT INTO elixir_ingredient
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (2, 4),
       (3, 2),
       (3, 3),
       (3, 4);

INSERT INTO users(name, email, enabled, coins)
VALUES ('validUser', 'validUser', true, 10000),
       ('invalidUser', 'invalidUser', true, 0);
INSERT INTO passwords(user_id, password)
VALUES(1, '$2a$10$sV32PjU5.1yGorVcVFZuieDb43LVQtPmlVhREgBLuq8axOa7nrkkG'),
      (2, '$2a$10$d7mMHb3zTWQxPXR1oswfduFR3zrFWnsPRn5TIyQTWP.V.uvW.pEhu');
INSERT INTO user_role(user_id, role_id)
VALUES(1, 1),
      (2, 1);
INSERT INTO user_ingredient(user_id, ingredient_id)
VALUES(1, 1),
      (1, 1),
      (1, 2),
      (1, 2),
      (1, 3),
      (1, 3),
      (1, 4),
      (1, 4);
INSERT INTO user_elixir(user_id, elixir_id)
VALUES(1, 1),
      (1, 1),
      (1, 2),
      (1, 2),
      (1, 3),
      (1, 3);

