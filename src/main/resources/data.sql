DELETE from api.student;
ALTER TABLE api.student ALTER COLUMN id RESTART WITH 1;
INSERT INTO api.student (name, surname, age, mail, field_of_study) VALUES ('John', 'Smith',20, 'JSmith@gmail.com', 'A');
INSERT INTO api.student (name, surname, age, mail, field_of_study) VALUES ('Frank', 'Brawn',30, 'FBrawn@gmail.com', 'B');
INSERT INTO api.student (name, surname, age, mail, field_of_study) VALUES ('Bill', 'Bloom',40, 'BB@gmail.com', 'A');

DELETE from api.teacher;
ALTER TABLE api.teacher ALTER COLUMN id RESTART WITH 1;
INSERT INTO api.teacher (name, surname, age, mail, subject) VALUES ('Johnss', 'Well',29, 'JWell@gmailcom', 'ATeacher');
INSERT INTO api.teacher (name, surname, age, mail, subject) VALUES ('Jony', 'Cell',44, 'JCell@gmail.com', 'BTeacher');
INSERT INTO api.teacher (name, surname, age, mail, subject) VALUES ('July', 'Mell',99, 'JMell@gmail.com', 'CTeacher');
INSERT INTO api.teacher (name, surname, age, mail, subject) VALUES ('Janny', 'Bell',56, 'JBellh@gmail.com', 'ATeacher');
INSERT INTO api.teacher (name, surname, age, mail, subject) VALUES ('Janny', 'Sell',56, 'JBellh@gmail.com', 'ATeacher');
INSERT INTO api.teacher (name, surname, age, mail, subject) VALUES ('Jack', 'Bell',56, 'JBellh@gmai.lcom', 'ATeacher');