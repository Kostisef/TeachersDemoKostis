package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.models.CourseModel;
import com.kostis.teachersdemo.service.impl.DTOServiceImpl;
import com.kostis.teachersdemo.service.impl.CourseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CourseController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final CourseServiceImpl courseService;
    private final DTOServiceImpl dtoService;

    public CourseController(CourseServiceImpl courseService, DTOServiceImpl dtoService) {
        this.courseService = courseService;
        this.dtoService = dtoService;
    }


    @RequestMapping("/getCourseModel")
    @ResponseBody
    public CourseModel getCourseModel(Integer id) {
        Course course = courseService.getCourseById(id);

        return dtoService.convertCourseToModel(course);
    }


    @PostMapping("/addNewCourse")
    public String addNewCourse(CourseModel course, RedirectAttributes redirectAttributes){
        String growlMsg = "Course created successfully";
        try{
            courseService.createNewCourse(course);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to create course... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }

    @PostMapping("/deleteCourse")
    public String deleteCourse(CourseModel courseIncoming, RedirectAttributes redirectAttributes){
//        System.out.println("CourseId to delete: "+ courseIncoming.getId());
        String growlMsg = "Course deleted successfully";
        try{
            courseService.deleteCourse(courseIncoming);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            growlMsg = "Failed to delete course... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }


    @PostMapping("/saveCourse")
    public String saveCourse(CourseModel selectedCourse, RedirectAttributes redirectAttributes) {
//        System.out.println("CourseId to update: "+ selectedCourse.getId());
        String growlMsg = "Course updated successfully";
        try{
            courseService.saveCourse(selectedCourse);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to update course... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }

    @RequestMapping("/searchCourses")
    @ResponseBody
    public List<CourseModel> searchCourses(String searchValue) {
        return courseService.customSearchCourses(searchValue);
    }
}
