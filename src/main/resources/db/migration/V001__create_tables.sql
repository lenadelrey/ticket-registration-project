-- TODO: название миграций должно отражать информацию о содержимом. для этой мб имеет смысл назвать init
-- но названия 002 и 003 - однозначно недопустимы
create table "user"
(
--     TODO: необходима табуляция для улучшения читабельности, иначе текст сливается. Например:
--     user_id             bigserial           not null        primary key,
--     name                varchar(254)        not null,
--     email               varchar(254)        not null,
--     password            varchar(254)        not null,
--     date_of_birth       timestamp           not null,
--     deleted             boolean             not null,
--     role_name           varchar(20)         not null
-- TODO: в идеале - одинаковая табуляция в рамках миграции
    user_id       bigserial    not null primary key,
--     TODO: почему именно 254?)
    name          varchar(254) not null,
    email         varchar(254) not null,
    password      varchar(254) not null,
    date_of_birth timestamp    not null,
    deleted       boolean      not null,
    role_name     varchar(20)  not null
);

create table airline
(
    airline_id bigserial    not null primary key,
    name       varchar(254) not null
);

create table flight
(
    flight_id          bigserial not null primary key,
    fk_airline_from_id bigserial not null,
    fk_airline_to_id   bigserial not null,
    flight_date        timestamp not null,
    count_of_tickets   integer   not null,
--     TODO: ФК можно прописывать более лаконично
    constraint fk_airline_from_id foreign key (fk_airline_from_id) references airline (airline_id),
    constraint fk_airline_to_id foreign key (fk_airline_to_id) references airline (airline_id)
);

create table ticket
(
    ticket_id    bigserial not null primary key,
    fk_user_id   bigserial not null,
    fk_flight_id bigserial not null,
    constraint fk_user_id foreign key (fk_user_id) references "user" (user_id),
    constraint fk_flight_id foreign key (fk_flight_id) references flight (flight_id)
);
