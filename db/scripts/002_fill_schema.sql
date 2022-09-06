insert into engine (volume, power) values (2.0, 100);

insert into brand (name) values ('Рыдван');

insert into model (name) values ('Драккар');
insert into model (name) values ('Nuke');

insert into body (type, equipment, color) values ('Минивэн', 'Off-Road', 'Чёрный');

insert into transmission (type) values ('MT');
insert into transmission (type) values ('AT');

insert into driver (login, password) values ('Охотник', 'йцукен');

insert into car (engine_id, brand_id, model_id, body_id, transmission_id) values (1, 1, 1, 1, 1);
insert into car (engine_id, brand_id, model_id, body_id, transmission_id) values (1, 1, 1, 1, 2);
insert into car (engine_id, brand_id, model_id, body_id, transmission_id) values (1, 1, 2, 1, 1);

insert into driver_car (driver_id, car_id) values (1, 1);

insert into ad (description, car_id, photo, sold, driver_id, created) values ('111', 1, '\xDEADBEEF', false, 1, '2021-12-20');
insert into ad (description, car_id, photo, sold, driver_id, created) values ('222', 2, null, false, 1, '2021-06-20');
insert into ad (description, car_id, photo, sold, driver_id, created) values ('333', 3, null, false, 1, '2023-06-20');