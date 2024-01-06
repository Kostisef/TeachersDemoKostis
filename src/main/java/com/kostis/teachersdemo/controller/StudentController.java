package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.StudentModel;
import com.kostis.teachersdemo.service.impl.CourseServiceImpl;
import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final UserServiceImpl userService;
    private final CourseServiceImpl courseService;

    public StudentController(UserServiceImpl userService, CourseServiceImpl courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @RequestMapping("/getStudent")
    @ResponseBody
    public User getStudent(Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/getStudentModel")
    @ResponseBody
    public StudentModel getStudentModel(Integer id) {
        return userService.getStudentModelById(id);
    }


    @PostMapping("/addNewStudent")
    public String addNewStudent(User student, RedirectAttributes redirectAttributes){
        String growlMsg = "Student created successfully";
        try{
            userService.createNewUser(student, 2);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to create student...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        return MAIN_URL;
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(User studentIncoming, RedirectAttributes redirectAttributes){
        System.out.println("StudentId to delete: "+ studentIncoming.getId());
        String growlMsg = "Student deleted successfully";

        try{
            userService.deleteStudent(studentIncoming);
        } catch (Exception e){
            growlMsg = "Failed to delete student...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;

    }


    @PostMapping("/saveStudent")
    public String saveStudent(User selectedStudent, RedirectAttributes redirectAttributes) {
        System.out.println("StudentId to update: "+ selectedStudent.getId());
        String growlMsg = "Student updated successfully";

        try{
            userService.saveUser(selectedStudent, 2);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to update Student...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;
    }


    @PostMapping("/removeEnrolledCourse")
    public String removeEnrolledCourse(@RequestParam("selectedStudent.id") Integer selectedStudentId,
                                       @RequestParam("selectedCourse.id") Integer selectedCourseId,
                                       RedirectAttributes redirectAttributes) {
        System.out.println("StudentId to use: "+ selectedStudentId);
        System.out.println("CourseId to use: "+ selectedCourseId);
        String growlMsg = "Course removed from Student successfully";

        try{
            courseService.removeCourseFromStudent(selectedStudentId, selectedCourseId);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to remove course from Student...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;
    }

    @PostMapping("/addEnrolledCourse")
    public String addEnrolledCourse(@RequestParam("selectedStudent.id") Integer selectedStudentId,
                                       @RequestParam("selectedCourse.id") Integer selectedCourseId,
                                       RedirectAttributes redirectAttributes) {
        System.out.println("StudentId to use: "+ selectedStudentId);
        System.out.println("CourseId to use: "+ selectedCourseId);
        String growlMsg = "Course added to Student successfully";

        try{
            courseService.addCourseToStudent(selectedStudentId, selectedCourseId);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to add course to Student...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;
    }

}
