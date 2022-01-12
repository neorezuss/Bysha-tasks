--liquibase formatted sql
--changeset aliaksei:create-tables splitStatements:true endDelimiter:;
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(200) NOT NULL UNIQUE,
    email    VARCHAR(254) NOT NULL UNIQUE,
    password VARCHAR(254) NOT NULL,
    enabled  BOOLEAN      NOT NULL DEFAULT true,
    role     VARCHAR(100) NOT NULL DEFAULT 'ROLE_USER',
    coins    INTEGER      NOT NULL DEFAULT 100
);
CREATE TABLE ingredients
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL UNIQUE,
    type VARCHAR(100) NOT NULL,
    cost INTEGER      NOT NULL CHECK (cost >= 0)
);
CREATE TABLE elixirs
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(200) NOT NULL UNIQUE,
    cost  INTEGER      NOT NULL CHECK (cost >= 0),
    level INTEGER      NOT NULL CHECK (cost > 0)
);
CREATE TABLE elixir_ingredient
(
    elixir_id            INTEGER NOT NULL REFERENCES elixirs,
    ingredient_id        INTEGER NOT NULL REFERENCES ingredients,
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
--rollback DROP TABLE user_elixir
--rollback DROP TABLE user_ingredient
--rollback DROP TABLE elixir_ingredient
--rollback DROP TABLE elixirs
--rollback DROP TABLE ingredients
--rollback DROP TABLE users