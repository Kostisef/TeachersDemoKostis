INSERT INTO `ROLE` (NAME, DESCRIPTION) VALUES
    ('TEACHER', 'Ρόλος για τον χαρακτηρισμό του Teacher'),
    ('STUDENT', 'Ρόλος για τον χαρακτηρισμό του STUDENT'),
    ('ADMIN', 'Ρόλος για τον χαρακτηρισμό του Admin');


INSERT INTO `USER` (ROLE_ID, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, SEMESTER, START_YEAR) VALUES
    (1, 'Ευθύμιος', 'Κωστής', 'e.kostis', 'teacher@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', null, 1999),
    (1, 'Νικόλαος', 'Ξάνθου', 'n.xanthou', 'teacher2@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', null, 2006),
    (2, 'Χρήστος', 'Παπαδόπουλος', 'x.papadopoulos', 'student@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', 10, 2013),
    (2, 'Χρήστος2', 'Παπαδόπουλος2', 'x.papadopoulosss', 'student2@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', 8, 2014),
    (3, 'Ιωάννης', 'Γεωργίου', 'i.georgiou', 'admin@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', null, null);



INSERT INTO `COURSE` (NAME, DESCRIPTION, SEMESTER, TEACHER_ID) VALUES
    ('C++', 'An intermediate-level programming language with object-oriented features, widely used for system/software development and game programming.', 1, 1),
    ('JAVA I', 'Introduction to Java programming language, covering syntax, basic concepts, and foundational skills for software development.', 1, 2),
    ('JAVA II', 'Advanced Java concepts, including data structures, GUI development, and network programming, building on the fundamentals learned in Java I.', 2, 2),
    ('C', 'Foundational course in the C programming language, emphasizing procedural programming and system-level development.', 1, 1),
    ('C#', 'Comprehensive introduction to C# programming, focusing on its use in developing Windows applications, web services, and enterprise-level software.', 4, 2),
    ('Assembly', 'Exploration of low-level programming using Assembly language, delving into computer architecture, memory management, and hardware interaction.',1, 2);

INSERT INTO `STUDENT_COURSE_ASSOCIATION`(STUDENT_ID, COURSE_ID) VALUES
    (3, 1), (3,2), (3, 3), (3,5),
    (4, 1), (4, 3);