package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.User;

import java.util.List;

public interface IUserService {

    /**
     * Find All Teachers
     */
    List<User> getAllTeachers();

    /**
     * Find All Students
     */
    List<User> getAllStudents();

    /**
     * Find User By Id
     */
    User getUserById(Long id);

    /**
     * Find User By Email
     */
    User getUserByEmail(String email);

    /**
     * Save User
     */
    void saveUser(User user);

    /**
     * Delete User
     */

    void deleteUser(Long id);

    void createNewTeacher(User teacher);
}
