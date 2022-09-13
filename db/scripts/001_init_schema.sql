create table if not exists engine(
    id serial primary key,
    volume float,
    power int
);

create table if not exists  brand(
    id serial primary key,
    name varchar(255)
);

create table if not exists  model(
    id serial primary key,
    name varchar(255)
);

create table if not exists body(
    id serial primary key,
    type varchar(255),
    equipment varchar(255),
    color varchar(255)
);

create table if not exists transmission(
                     id serial primary key,
                     type varchar(255)
);

create table if not exists driver(
    id serial primary key,
    login  varchar(255) UNIQUE,
    password varchar(255)
);

create table if not exists car(
                    id serial primary key,
                    engine_id int not null references engine(id),
                    brand_id int not null references brand(id),
                    model_id int not null references model(id),
                    body_id int not null references body(id),
                    transmission_id int not null references transmission(id)
);

create table if not exists driver_car(
                              id serial primary key,
                              driver_id int not null references driver(id),
                              car_id int not null references car(id)
);

create table if not exists ad(
                   id serial primary key,
                   description text,
                   car_id int not null references car(id),
                   photo bytea,
                   sold boolean,
                   driver_id int not null references driver(id),
                   created timestamp
);

