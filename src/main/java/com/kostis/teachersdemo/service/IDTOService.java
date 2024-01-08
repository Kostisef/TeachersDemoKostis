package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.CourseModel;
import com.kostis.teachersdemo.models.RoleModel;
import com.kostis.teachersdemo.models.StudentModel;
import com.kostis.teachersdemo.models.TeacherModel;

import java.util.List;

public interface IDTOService {
    TeacherModel convertUserToTeacherModel(User teacher);

    StudentModel convertUserToStudentModel(User student);

    List<CourseModel> populateCourseModelList(User student);

    List<CourseModel> populateNotAttendingCourses(StudentModel studentModel);

    User convertTeacherModelToUser(TeacherModel model);

    User convertStudentModelToUser(StudentModel model);

    CourseModel convertCourseToModel(Course course);

    Course convertModelToCourse(CourseModel model);

    List<CourseModel> convertCourseListToModelList(List<Course> courseList);

    Role convertModelToRole(RoleModel model);
}
