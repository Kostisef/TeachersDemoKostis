package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.StudentModel;
import com.kostis.teachersdemo.models.TeacherModel;

import java.util.List;

public interface IUserService {

    /**
     * Find All Teachers
     */
    List<TeacherModel> getAllTeacherModels();

    /**
     * Find All Students
     */
    List<StudentModel> getAllStudentModels();


    User getUserById(Integer id);


    void saveTeacher(TeacherModel teacherModel) throws Exception;

    void saveStudent(StudentModel studentModel) throws Exception;


    void createNewStudent(StudentModel studentModel, String rawPassword);

    void createNewTeacher(TeacherModel teacherModel, String rawPassword);

    StudentModel getStudentModelById(Integer id);
}
