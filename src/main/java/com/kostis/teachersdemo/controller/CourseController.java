package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.repo.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CourseController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @PostMapping("/addNewCourse")
    public String addNewCourse(Course course){
        courseRepository.save(course);

        return MAIN_URL;
    }

    @PostMapping("/deleteCourse")
    public String deleteCourse(@RequestParam Integer courseId){
        System.out.println("CourseId to delete: "+ courseId);

        Course courseToDelete = getCourse(courseId);
        courseRepository.delete(courseToDelete);

        return MAIN_URL;
    }

    @RequestMapping("/getCourse")
    @ResponseBody
    public Course getCourse(Integer id) {
        return courseRepository.findById(id);
    }

    @PostMapping("/saveCourse")
    public String saveCourse(Course selectedCourse) {

        Course courseToUpdate = courseRepository.findById(selectedCourse.getId());

        if (courseToUpdate !=null){
            // Convert model to entity!
//            courseToUpdate.setFirstname(selectedCourse.getFirstname());
//            courseToUpdate.setLastname(selectedCourse.getLastname());
//            courseToUpdate.setUsername(selectedCourse.getUsername());
//            courseToUpdate.setEmail(selectedCourse.getEmail());
//            courseToUpdate.setStartYear(selectedCourse.getStartYear());
//            courseToUpdate.setSemester(selectedCourse.getSemester());

            // Save the edited student to the database
            courseRepository.save(courseToUpdate);
        }

        return MAIN_URL;
    }
}
