alter table "user"
    rename column user_id to id;

alter table ticket
    rename column ticket_id to id;

alter table flight
    rename column flight_id to id;

alter table airline
    rename column airline_id to id;