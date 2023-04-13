create table user
(
    id         bigint auto_increment
        primary key,
    name       varchar(255)               not null,
    created_at datetime(6) default now(6) not null,
    updated_at datetime(6) default now(6) not null
);
