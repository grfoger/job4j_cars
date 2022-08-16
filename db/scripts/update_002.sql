create table account(
                        id serial primary key,
                        login  varchar(255) UNIQUE,
                        password varchar(255)
);

create table ad(
    id serial primary key,
    description text,
    brand varchar(255),
    model varchar(255),
    body varchar(255),
    photo bytea,
    sold boolean,
    account_id int not null references account(id)
);
