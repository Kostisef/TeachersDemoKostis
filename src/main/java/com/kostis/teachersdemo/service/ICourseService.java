package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Course;

import java.util.List;

public interface ICourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    void saveCourse(Course course);

    void deleteCourse(Long id);
}
