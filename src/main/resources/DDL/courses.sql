CREATE DATABASE  IF NOT EXISTS secure_course_management_directory;
USE secure_course_management_directory;

DROP TABLE IF EXISTS student_registrations;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS students;

create table users (
    username varchar(50) not null primary key,
    password varchar(120) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username)
);

create table courses (
    id               int not null primary key auto_increment,
    name             varchar(45)  default null,
    semester         int          default null,
    year             int          default null,
    instructor       varchar(45)  default null,
    syllabus         varchar(150) default null,
    instructor_login varchar(50)  default null,
    foreign key (instructor_login) references users(username)
);

create table student_registrations (
    student_id int primary key auto_increment,
    course_id int default null,
    name varchar(100) default null,
    email varchar(150) default null,
    year int default null,
    project_grade double default null,
    exam_grade double default null,
    foreign key (course_id) references courses(id)  on delete cascade
);


--
-- Data for `instructors`
--

insert into users(username, password, enabled) values
    ('zarras','{noop}zarras',true),
    ('dimako','{noop}dimako',true),
    ('manis','{noop}manis', true);

insert into authorities(username, authority) values
    ('zarras','ROLE_ADMIN'),
    ('dimako','ROLE_ADMIN'),
    ('manis','ROLE_ADMIN');

--
-- Data for table `courses`
--

insert into courses(id, name, semester, year, instructor, syllabus, instructor_login) values
    (1, 'MYY803', 8, 4, 'Apostolos Zarras', 'Software Development Class', 'zarras'),
    (2, 'MYE004', 8, 4, 'Apostolos Zarras', 'Software Engineering II Class', 'zarras'),
    (3, 'MYY802', 8, 4, 'Manis Georgios', 'Compilers Class', 'manis');

--
-- Data for table `student_registrations`
--

insert INTO student_registrations VALUES
    (1, 1, 'Leslie Andrews', 'leslie@test.com', 4, 5.0, 5.0),
    (2, 1, 'Emma Baumgarten', 'emma@test.com', 4, 3.0, 8.0),
    (3, 1, 'Avani Gupta', 'avani@test.com', 4, 10.0, 5.0),
    (4, 1, 'Yuri Petrov', 'yuri@test.com', 4, 10.0, 10.0),
    (6, 2, 'Yuri Petrov', 'yuri@test.com', 4, 0.0, 0.0),
    (5, 2, 'Juan Vega', 'juan@test.com', 4, 0.0, 0.0),
    (7, 1, 'Mike Dowers', 'mike@test.com', 4, 0.0, 0.0);



