package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final UserServiceImpl userService;

    public StudentController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/getStudent")
    @ResponseBody
    public User getStudent(Integer id) {
        return userService.getUserById(id);
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

}
