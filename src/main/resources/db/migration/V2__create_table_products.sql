-- Создание таблицы products
CREATE TABLE IF NOT EXISTS products
(
    id             bigserial PRIMARY KEY,
    account_number VARCHAR(80) CONSTRAINT account_number UNIQUE,
    balance        NUMERIC(20, 2),
    type           VARCHAR(20),
    user_id        bigserial -- умышленно без FK согласно комментариям к ДЗ
);

-- Начальное заполнение таблицы products
INSERT INTO products(account_number, balance, type, user_id)
VALUES ('40503810658040000111', 1200.00, 'ACCOUNT', 1),
       ('40703810900560008222', 500.00, 'CARD', 1),
       ('40702810500000000333', 30000.00, 'CARD', 1),
       ('40702810500000000444', 655.50, 'ACCOUNT', 2),
       ('40702810500000000555', 25000.00, 'CARD', 2),
       ('40702810500000000666', 723.38, 'ACCOUNT', 3)
ON CONFLICT (account_number) DO NOTHING;