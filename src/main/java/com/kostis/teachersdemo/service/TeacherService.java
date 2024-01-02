package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.User;

import java.util.List;

public interface TeacherService {

    /**
     * FIND ALL
     */
    List<User> getAllTeachers();

    /**
     * FIND BY ID
     */
    User getTeacherById(Long id);

    /**
     * FIND BY ID
     */
    User getTeacherByEmail(String email);

    /**
     * Save
     */
    void saveTeacher(User user);

    /**
     * Delete
     */

    void deleteTeacher(Long id);
}
