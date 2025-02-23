-- Создание таблицы users
CREATE TABLE IF NOT EXISTS users
(
    id          bigserial    PRIMARY KEY,
    username    VARCHAR(255) CONSTRAINT unique_username UNIQUE
);

-- Начальное заполнение таблицы users
INSERT INTO users(username)
VALUES ('Ivan'),
       ('Maria'),
       ('Petr')