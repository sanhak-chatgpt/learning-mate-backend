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
    major_id     bigint                     not null,
    subject_name varchar(255)               not null,
    description  varchar(255)               not null,
    created_at   datetime(6) default now(6) not null,
    updated_at   datetime(6) default now(6) not null,
    constraint major_id
        foreign key (major_id) references major (id)
);

create table topic
(
    id          bigint auto_increment
        primary key,
    subject_id  bigint                     not null,
    topic_name  varchar(255)               not null,
    description varchar(255)               not null,
    created_at  datetime(6) default now(6) not null,
    updated_at  datetime(6) default now(6) not null,
    constraint subject_id
        foreign key (subject_id) references subject (id)
);

create table lecture
(
    id                 bigint auto_increment
        primary key,
    topic_id           bigint                                     not null,
    user_id            bigint                                     not null,
    audio_url          varchar(255)                               not null,
    transcribed        TEXT                                       null,
    score              INTEGER                                    null,
    strength           TEXT                                       null,
    weakness           TEXT                                       null,
    status             varchar (255)                              not null,
    helpfulness_rating INTEGER                                    null,
    created_at         DATETIME(6) default now(6)                 not null,
    updated_at         DATETIME(6) default now(6)                 not null,
    constraint topic_id
        foreign key (topic_id) references topic (id),
    constraint user_id
        foreign key (user_id) references user (id)
);
