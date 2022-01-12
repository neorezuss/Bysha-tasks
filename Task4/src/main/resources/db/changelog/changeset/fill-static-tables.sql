--liquibase formatted sql
--changeset aliaksei:fill-static-tables splitStatements:true endDelimiter:;
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