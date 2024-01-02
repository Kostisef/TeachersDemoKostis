package com.kostis.TeachersDemoKostis.service;

import com.kostis.TeachersDemoKostis.entities.Teacher;

import java.util.List;

public interface TeacherService {

    /**
     * FIND ALL
     */
    List<Teacher> getAllTeachers();

    /**
     * FIND BY ID
     */
    Teacher getTeacherById(Long id);

    /**
     * FIND BY ID
     */
    Teacher getTeacherByEmail(String email);

    /**
     * Save
     */
    void saveTeacher(Teacher teacher);

    /**
     * Delete
     */

    void deleteTeacher(Long id);
}
