package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.service.ICourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public void saveCourse(Course course) throws Exception {
        Course courseToUpdate = courseRepository.findById(course.getId());

        if (courseToUpdate!=null){
            courseToUpdate.setName(course.getName());
            courseToUpdate.setDescription(course.getDescription());
            courseToUpdate.setSemester(course.getSemester());

            courseRepository.save(courseToUpdate);
        } else {
            throw new Exception("Null incoming course to update.");
        }
    }

    @Override
    public void deleteCourse(Course course) throws Exception {
        Course courseToDelete = getCourseById(course.getId());

        if (courseToDelete !=null){
            courseRepository.delete(courseToDelete);
        } else {
            throw new Exception("Null incoming course to delete.");
        }
    }

    @Override
    public void createNewCourse(Course course){
        course.setId(null);
        courseRepository.save(course);
    }
}
