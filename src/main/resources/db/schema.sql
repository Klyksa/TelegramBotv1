-- Таблица для активных чатов
CREATE TABLE ACTIVE_CHAT (
    ID SERIAL PRIMARY KEY NOT NULL,  -- Уникальный идентификатор для записи (первичный ключ)
    CHAT_ID INTEGER NOT NULL          -- Идентификатор чата (не может быть NULL)
);

-- Таблица для учета доходов
CREATE TABLE INCOME (
    ID SERIAL PRIMARY KEY NOT NULL,  -- Уникальный идентификатор для записи (первичный ключ)
    CHAT_ID INTEGER NOT NULL,         -- Идентификатор чата (не может быть NULL)
    INCOME INTEGER                    -- Сумма дохода (может быть NULL, если не указано)
);

-- Таблица для учета расходов
CREATE TABLE SPEND (
    ID SERIAL PRIMARY KEY NOT NULL,   -- Уникальный идентификатор для записи (первичный ключ)
    CHAT_ID INTEGER NOT NULL,          -- Идентификатор чата (не может быть NULL)
    SPEND INTEGER                      -- Сумма расходов (может быть NULL, если не указано)
);