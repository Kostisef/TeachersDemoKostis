-- -- Insert roles
-- # INSERT INTO ROLE (ROLE_NAME, ROLE_DESCR, INS_DATE, LAST_DATE_UPD) VALUES
-- #                                                                       ('ADMIN', 'Administrator', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- #                                                                       ('TEACHER', 'Teacher', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- #                                                                       ('STUDENT', 'Student', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- #
-- # -- Insert contact information
-- # INSERT INTO CONTACT_INFO (FIRST_NAME, LAST_NAME, EMAIL, PHONE, INS_DATE, LAST_DATE_UPD) VALUES
-- #                                                                                             ('John', 'Doe', 'john.doe@example.com', '123-456-7890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- #                                                                                             ('Jane', 'Smith', 'jane.smith@example.com', '987-654-3210', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- #
-- # -- Insert courses
-- # INSERT INTO COURSE (COURSE_NAME, SEMESTER, INS_DATE, LAST_DATE_UPD) VALUES
-- #                                                                         ('Math 101', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- #                                                                         ('History 202', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- #                                                                         ('Physics 301', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- #
-- # -- Insert students
-- # INSERT INTO STUDENT (ROLE_ID, CONTACT_INFO, SEMESTER, INS_DATE, LAST_DATE_UPD) VALUES
-- #                                                                                    (3, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- #                                                                                    (3, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- #
-- # -- Insert teachers
-- # INSERT INTO TEACHER (ROLE_ID, CONTACT_INFO, INS_DATE, LAST_DATE_UPD) VALUES
-- #                                                                          (2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- #                                                                          (2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- CREATE TABLE IF NOT EXISTS TEACHERS (
--     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
--     FNAME VARCHAR(255),
--     LNAME VARCHAR(255),
--     USERNAME VARCHAR(255),
--     EMAIL VARCHAR(255),
--     PASSWORD VARCHAR(255),
--     TELEPHONE VARCHAR(20)
-- );
-- Insert sample data if needed
--
INSERT INTO `TEACHER` (F_NAME, L_NAME, USERNAME, EMAIL, PASSWORD, TELEPHONE) VALUES
('Efthimis', 'Kostis', 'e.kostis', 'test@gmail.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', '1234567890');

INSERT INTO `TEACHER` (F_NAME, L_NAME, USERNAME, EMAIL, PASSWORD, TELEPHONE) VALUES
('Dimitris', 'Kostis', 'd.kostis', 'test2@gmail.com', '$2a$10$2tzGWztiPorX3D2vYwoAnu4CLpdnwS96HZGj5NDVdVv3d.n22xkVW', '1234567890');
