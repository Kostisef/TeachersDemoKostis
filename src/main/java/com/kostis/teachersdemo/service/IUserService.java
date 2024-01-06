package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.StudentModel;

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

    User getUserById(Integer id);


    void saveUser(User user, Integer roleId) throws Exception;


    void createNewUser(User user, Integer roleId);

    void removeTeachingCourse(User selectedTeacher, Course selectedCourse);

    StudentModel getStudentModelById(Integer id);
}
