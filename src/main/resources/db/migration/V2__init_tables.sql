create table user
(
    id         bigint auto_increment
        primary key,
    name       varchar(255)               not null,
    created_at datetime(6) default now(6) not null,
    updated_at datetime(6) default now(6) not null
);

create table major
(
    id          bigint auto_increment
        primary key,
    major_name  varchar(255)               not null,
    description varchar(255)               not null,
    created_at  datetime(6) default now(6) not null,
    updated_at  datetime(6) default now(6) not null
);

create table subject
(
    id           bigint auto_increment
        primary key,
    subject_name varchar(255)               not null,
    description  varchar(255)               not null,
    created_at   datetime(6) default now(6) not null,
    updated_at   datetime(6) default now(6) not null,
    constraint major_id
        foreign key (id) references major (id)
);

create table topic
(
    id          bigint auto_increment
        primary key,
    topic_name  varchar(255)               not null,
    description varchar(255)               not null,
    created_at  datetime(6) default now(6) not null,
    updated_at  datetime(6) default now(6) not null,
    constraint subject_id
        foreign key (id) references subject (id)
);

create table lecture
(
    id                bigint auto_increment
        primary key,
    audio_url         varchar(255)                               not null,
    transcribed       text                                       null,
    score             integer                                    null,
    strength          text                                       null,
    weakness          text                                       null,
    status            enum ('IN_PROGRESS', 'SUCCESS', 'FAILURE') not null,
    helpfulnessRating integer                                    null,
    created_at        datetime(6) default now(6)                 not null,
    updated_at        datetime(6) default now(6)                 not null,
    constraint topic_id
        foreign key (id) references topic (id),
    constraint user_id
        foreign key (id) references user (id)
);