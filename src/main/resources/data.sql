INSERT INTO `ROLE` (NAME, DESCRIPTION) VALUES
    ('TEACHER', 'Ρόλος για τον Teacher'),
    ('STUDENT', 'Ρόλος για τον STUDENT'),
    ('ADMIN', 'Ρόλος για τον Admin');


INSERT INTO `USER` (ROLE_ID, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, TEL, SEMESTER, START_YEAR) VALUES
    (1, 'Test [Teacher1]', 'Kostis', 'e.kostis', 'teacher@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', '1234567890', 2013, null),
    (2, 'Test [Student]', 'Kostis', 'e.kostis2', 'studentt@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', '1234567890', 2023, 1),
    (3, 'Test [Admin]', 'Kostis', 'e.kostis3', 'admin@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', '1234567890', null, null),
    (1, 'Test [Teacher2]', 'Kostis', 'e.kostis4', 'teacher2@test.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', '1234567890', 2013, null);


INSERT INTO `COURSE` (NAME, DESCRIPTION, SEMESTER, TEACHER_ID) VALUES
    ('C++', 'TEST1', 1, 1),
    ('JAVA I', 'TEST2', 1, 4),
    ('JAVA II', 'TEST3', 2, 4),
    ('C', 'TEST4', 1, 1),
    ('C#', 'TEST5', 4, 1),
    ('ASSEMBLY', 'TEST6',1, 1);
