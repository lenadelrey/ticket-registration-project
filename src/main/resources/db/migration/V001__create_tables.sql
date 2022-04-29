-- TODO: название миграций должно отражать информацию о содержимом. для этой мб имеет смысл назвать init
-- но названия 002 и 003 - однозначно недопустимы
create table "user"
(
    user_id                 bigserial           not null        primary key,
    name                    varchar(250)        not null,
    email                   varchar(250)        not null,
    password                varchar(250)        not null,
    birth_date              timestamp           not null,
    deleted                 boolean             not null,
    role_name               varchar(20)         not null
);

create table airline
(
    airline_id              bigserial           not null        primary key,
    name                    varchar(254)        not null
);

create table flight
(
    flight_id               bigserial           not null        primary key,
    fk_airline_from_id      bigserial           not null        references airline (airline_id),
    fk_airline_to_id        bigserial           not null        references airline (airline_id),
    flight_date             timestamp           not null,
    count_of_tickets        integer             not null
);

create table ticket
(
    ticket_id               bigserial           not null        primary key,
    fk_user_id              bigserial           not null        references "user" (user_id),
    fk_flight_id            bigserial           not null        references flight (flight_id)
);
