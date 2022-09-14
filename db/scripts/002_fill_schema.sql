insert into engine (volume, power) values (6.0, 1000);
insert into engine (volume, power) values (2.0, 90);
insert into engine (volume, power) values (3.0, 150);
insert into engine (volume, power) values (1.0, 50);

insert into brand (name) values ('Рыдван');
insert into brand (name) values ('Марапацуца');

insert into model (name) values ('Драккар');
insert into model (name) values ('Nuke');
insert into model (name) values ('Дерижбомбиль');
insert into model (name) values ('Цепелин');

insert into body (type, equipment, color) values ('Минивэн', 'Off-Road', 'Чёрный');
insert into body (type, equipment, color) values ('Седан', 'Time-Trip', 'Серый');
insert into body (type, equipment, color) values ('Аэростат', 'Long type', 'Охра');

insert into transmission (type) values ('MT');
insert into transmission (type) values ('AT');

insert into driver (login, password) values ('Охотник', 'йцукен');
insert into driver (login, password) values ('Граф', 'цукини');
insert into driver (login, password) values ('1', '1');

insert into car (engine_id, brand_id, model_id, body_id, transmission_id) values (1, 1, 1, 1, 1);
insert into car (engine_id, brand_id, model_id, body_id, transmission_id) values (2, 1, 2, 2, 2);
insert into car (engine_id, brand_id, model_id, body_id, transmission_id) values (3, 2, 3, 3, 2);
insert into car (engine_id, brand_id, model_id, body_id, transmission_id) values (4, 2, 4, 3, 1);


insert into driver_car (driver_id, car_id) values (1, 1);
insert into driver_car (driver_id, car_id) values (1, 2);
insert into driver_car (driver_id, car_id) values (2, 3);
insert into driver_car (driver_id, car_id) values (2, 4);

insert into ad (description, car_id, photo, sold, driver_id, created) values ('Зверь-машина, на атомном ходу. Отдам за цистерну кваса.', 1, null, false, 1, '2021-12-16');
insert into ad (description, car_id, photo, sold, driver_id, created) values ('Когда эта крошка разгонится до 88 миль в час, ты такое увидишь…', 2, null, false, 1, '2023-06-14');
insert into ad (description, car_id, photo, sold, driver_id, created) values ('Идеальный агрегат для опыления картошки с воздуха. Отдам за так.', 3, null, false, 2, '2021-06-15');
insert into ad (description, car_id, photo, sold, driver_id, created) values ('Великий сталкер чёрного неба. 3 млн. руб.', 4, null, false, 2, '2022-06-20');