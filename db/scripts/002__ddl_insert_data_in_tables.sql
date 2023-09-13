<!--Данный список содержит тестовые данные в базе данных-->

INSERT INTO client (name, surname, address, phone)
VALUES ('Ivan', 'Ivanov', 'Russia; Moscow; Novosibirskaya 14', '89588545566'),
       ('Petr', 'Petrov', 'Russia; Moscow; Krasnoznamenskaya 18', '89996523311'),
       ('Andrey', 'Andreev', 'Russia; Moscow; Novoslobodskaya 2', '89561454477');

INSERT INTO delivery_man (name, surname, phone)<!---->
VALUES ('Fahid', 'Alimov', '89654782265'),
       ('Artem', 'Ivanov', '89842561124'),
       ('Evgeniy', 'Petrov', '89875562122');

INSERT INTO menu (food_name, weight, price)<!---->
VALUES ('Burger', 160, 250),
       ('Cream-soup', 60, 100),
       ('Borsh', 60, 120),
       ('Pizza', 485, 789);

INSERT INTO "order" (client_id, delivery_man_id, order_date, when_deliver, note)<!---->
VALUES (1, 1, '2023-07-19 10:23:54', '2023-07-19 11:23:54', 'description...'),
       (2, 2, '2023-07-19 17:23:00', '2023-07-19 18:23:00', 'description...'),
       (3, 3, '2023-07-19 18:30:00', '2023-07-19 19:30:00', 'description...');

INSERT INTO order_detail (order_id, menu_id, serving_count)<!---->
VALUES (1, 1, 2),
       (2, 3, 1),
       (3, 4, 3);