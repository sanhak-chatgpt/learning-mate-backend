create table student
(
    id         bigint auto_increment primary key,
    name       varchar(16) not null,
    student_id bigint      not null
);

insert into student (name, student_id) values ('구영민', 2016125003);
insert into student (name, student_id) values ('이인', 2021125050);
insert into student (name, student_id) values ('최경태', 2019125066);
insert into student (name, student_id) values ('허수민', 2021124193);
insert into student (name, student_id) values ('김재욱', 2020124029);
insert into student (name, student_id) values ('전영서', 2020125061);
