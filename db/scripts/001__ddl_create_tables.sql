CREATE TABLE IF NOT EXISTS client /*Создать таблицу если нет клиента*/
(
    id      SERIAL PRIMARY KEY, /*Идентификационный номер клиента*/
    name    VARCHAR, /*Имя*/
    surname VARCHAR, /*Фамилия*/
    address TEXT, /*Адресс*/
    phone   TEXT /*Номер телефона*/
);

CREATE TABLE IF NOT EXISTS delivery_man /*Создать таблицу если нет курьера*/
(
    id      SERIAL PRIMARY KEY, /*Идентификационный номер курьера*/
    name    VARCHAR, /*Имя*/
    surname VARCHAR, /*Фамилия*/
    phone   TEXT /*Номер телефона*/
);

CREATE TABLE IF NOT EXISTS menu /*Создать таблицу если нет меню*/
(
    id        SERIAL PRIMARY KEY, /*Идентификационный номер меню*/
    food_name VARCHAR, /*Назватие блюда*/
    weight    INTEGER, /*Масса*/
    price     INTEGER /*Цена*/
);

CREATE TABLE IF NOT EXISTS "order" /*Создать таблицу если нет заказа*/
(
    id              SERIAL PRIMARY KEY, /*Идентификационный номер заказа который выдаётся клиенту*/
    client_id       INTEGER REFERENCES client (id), /*Идентификационный номер клиента которому выдаётся заказ*/
    delivery_man_id INTEGER REFERENCES delivery_man (id), /*Идентификационный номер курьера который доставляет заказ клиенту*/
    order_date      TIMESTAMP, /*Дата оформления заказа клиентом*/
    when_deliver    TIMESTAMP, /*Дата удобного времени доставки заказа*/
    note            TEXT, /*Текстовые примечания клиентом при заказе*/
    CONSTRAINT date_check CHECK (order_date < when_deliver)
);

CREATE TABLE IF NOT EXISTS order_detail /*Создать таблицу если нет деталей заказа*/
(
    id            SERIAL PRIMARY KEY, /*Идентификационный номер деталей заказа*/
    order_id      INTEGER REFERENCES "order" (id), /*Идентификационный номер заказа*/
    menu_id       INTEGER REFERENCES menu (id), /*Идентификационный номер меню*/
    serving_count INTEGER /*Количество порций*/
);