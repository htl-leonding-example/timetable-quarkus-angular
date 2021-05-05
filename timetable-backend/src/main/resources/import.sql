insert into teacher (id, firstname, lastname, room) values (1000, 'Gerald', 'Aistleitner', '216');
insert into teacher (id, firstname, lastname, room) values (1001, 'Herbert', 'Lackinger', '108');
insert into teacher (id, firstname, lastname, room) values (1002, 'David', 'Klewein', '216');
insert into teacher (id, firstname, lastname, room) values (1003, 'Michael', 'Bucek', '108');
insert into teacher (id, firstname, lastname, room) values (1004, 'Thomas', 'Stuetz', 'E152');
insert into teacher (id, firstname, lastname, room) values (1005, 'Markus', 'Haslinger', '134');

insert into schoolclass(id, room) values ('5AHITM', '135');
insert into schoolclass(id, room) values ('5BHITM', 'E58-2');

insert into unit (id, day, unit, subject, TEACHER_ID, SCHOOLCLASS_ID) values (1000, 1, 1, 'SEW', 1000, '5BHITM');
insert into unit (id, day, unit, subject, TEACHER_ID, SCHOOLCLASS_ID) values (1001, 1, 2, 'SEW', 1000, '5BHITM');
insert into unit (id, day, unit, subject, TEACHER_ID, SCHOOLCLASS_ID) values (1002, 1, 3, 'ITP', 1001, '5BHITM');

