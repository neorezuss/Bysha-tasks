--liquibase formatted sql
--changeset aliaksei:create-tables splitStatements:true endDelimiter:;
CREATE TABLE users
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(200) NOT NULL UNIQUE,
    email   VARCHAR(255) NOT NULL UNIQUE,
    enabled BOOLEAN      NOT NULL DEFAULT true
);
CREATE TABLE passwords
(
    user_id  BIGINT PRIMARY KEY REFERENCES users,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE user_role
(
    user_id BIGINT REFERENCES users,
    role_id BIGINT REFERENCES roles,
    PRIMARY KEY (user_id, role_id)
);
CREATE TABLE user_inventories
(
    user_id BIGINT PRIMARY KEY REFERENCES users,
    coins   INTEGER NOT NULL DEFAULT 100
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
    elixir_id     BIGINT NOT NULL REFERENCES elixirs,
    ingredient_id BIGINT NOT NULL REFERENCES ingredients,
    PRIMARY KEY (elixir_id, ingredient_id)
);
CREATE TABLE user_inventory_ingredient
(
    id                BIGSERIAL PRIMARY KEY,
    user_inventory_id INTEGER NOT NULL REFERENCES user_inventories,
    ingredient_id     INTEGER NOT NULL REFERENCES ingredients
);
CREATE TABLE user_inventory_elixir
(
    id                BIGSERIAL PRIMARY KEY,
    user_inventory_id INTEGER NOT NULL REFERENCES user_inventories,
    elixir_id         INTEGER NOT NULL REFERENCES elixirs
);

--rollback DROP TABLE user_inventory_ingredient
--rollback DROP TABLE user_inventory_elixir
--rollback DROP TABLE elixir_ingredient
--rollback DROP TABLE user_inventories
--rollback DROP TABLE elixirs
--rollback DROP TABLE ingredients
--rollback DROP TABLE user_role
--rollback DROP TABLE roles
--rollback DROP TABLE passwords
--rollback DROP TABLE users