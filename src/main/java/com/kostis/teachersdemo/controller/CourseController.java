package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.models.CourseModel;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.service.impl.CourseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/getCourse")
    @ResponseBody
    public Course getCourse(Integer id) {
        return courseService.getCourseById(id);
    }

    @RequestMapping("/getCourseModel")
    @ResponseBody
    public CourseModel getCourseModel(Integer id) {
        Course course = courseService.getCourseById(id);
        CourseModel model = new CourseModel();

        model.setId(course.getId());
        model.setName(course.getName());
        model.setDescription(course.getDescription());
        model.setSemester(course.getSemester());
        String teacherFullName = "-";
        if (course.getTeacher() != null){
            teacherFullName = course.getTeacher().getLastname() + " " + course.getTeacher().getFirstname();
        }
        model.setTeacherFullName(teacherFullName);

        return model;
    }


    @PostMapping("/addNewCourse")
    public String addNewCourse(Course course, RedirectAttributes redirectAttributes){
        String growlMsg = "Course created successfully";
        try{
            courseService.createNewCourse(course);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to create course...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;
    }

    @PostMapping("/deleteCourse")
    public String deleteCourse(Course courseIncoming, RedirectAttributes redirectAttributes){
        System.out.println("CourseId to delete: "+ courseIncoming.getId());

        String growlMsg = "Course deleted successfully";
        try{
            courseService.deleteCourse(courseIncoming);
        } catch (Exception e){
            growlMsg = "Failed to delete course...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;
    }


    @PostMapping("/saveCourse")
    public String saveCourse(Course selectedCourse, RedirectAttributes redirectAttributes) {
        System.out.println("CourseId to update: "+ selectedCourse.getId());
        String growlMsg = "Course updated successfully";
        try{
            courseService.saveCourse(selectedCourse);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to update course...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        return MAIN_URL;
    }
}
