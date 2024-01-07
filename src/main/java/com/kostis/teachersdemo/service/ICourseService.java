package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.CourseModel;

import java.util.List;

public interface ICourseService {

    List<CourseModel> getAllCourseModels();

    Course getCourseById(Integer id);

    void saveCourse(CourseModel course) throws Exception;

    void deleteCourse(CourseModel course) throws Exception;

    void createNewCourse(CourseModel course);

    void removeTeacherFromCourse(Integer selectedTeacherId, Integer selectedCourseId) throws Exception;

    List<CourseModel> getAllCoursesWithoutTeacher();

    void addTeacherToCourse(Integer selectedTeacherId, Integer selectedCourseId) throws Exception;

    void removeCourseFromStudent(Integer selectedStudentId, Integer selectedCourseId) throws Exception;

    void addCourseToStudent(Integer selectedStudentId, Integer selectedCourseId) throws Exception;
}
