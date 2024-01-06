package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.User;

import java.util.List;

public interface ICourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course getCourseById(Integer id);

    void saveCourse(Course course) throws Exception;

    void deleteCourse(Course course) throws Exception;

    void createNewCourse(Course course);

    void removeTeacherFromCourse(Integer selectedTeacherId, Integer selectedCourseId) throws Exception;

    List<Course> getAllCoursesWithoutTeacher();

    void addTeacherToCourse(Integer selectedTeacherId, Integer selectedCourseId) throws Exception;

    void removeCourseFromStudent(Integer selectedStudentId, Integer selectedCourseId) throws Exception;

    void addCourseToStudent(Integer selectedStudentId, Integer selectedCourseId) throws Exception;
}
