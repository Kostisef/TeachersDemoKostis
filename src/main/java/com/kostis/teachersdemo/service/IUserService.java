package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.StudentModel;
import com.kostis.teachersdemo.models.TeacherModel;

import java.util.List;

public interface IUserService {

    List<TeacherModel> getAllTeacherModels();

    List<StudentModel> getAllStudentModels();

    User getUserById(Integer id);

    void saveTeacher(TeacherModel teacherModel) throws Exception;

    void saveStudent(StudentModel studentModel) throws Exception;

    void deleteTeacher(TeacherModel teacher);

    void deleteStudent(StudentModel student);

    void createNewStudent(StudentModel studentModel, String rawPassword);

    void createNewTeacher(TeacherModel teacherModel, String rawPassword);

    StudentModel getStudentModelById(Integer id);

    List<TeacherModel> customSearchTeachers(String searchValue);

    List<StudentModel> customSearchStudents(String searchValue);
}
