package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeacherController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final UserServiceImpl userService;

    public TeacherController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/getTeacher")
    @ResponseBody
    public User getTeacher(Integer id) {
        return userService.getUserById(id);
    }


    @PostMapping("/addNewTeacher")
    public String addNewTeacher(User teacher, RedirectAttributes redirectAttributes){

        String growlMsg = "Teacher created successfully";
        try{
            userService.createNewUser(teacher, 1);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to create teacher...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        return MAIN_URL;
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(User teacherIncoming, RedirectAttributes redirectAttributes){
        System.out.println("TeacherId to delete: "+ teacherIncoming.getId());
        String growlMsg = "Teacher deleted successfully";
        try{
            userService.deleteTeacher(teacherIncoming);
        } catch (Exception e){
            growlMsg = "Failed to delete teacher...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;
    }


    @PostMapping("/saveTeacher")
    public String saveTeacher(User selectedTeacher, RedirectAttributes redirectAttributes) {
        System.out.println("TeacherId to update: "+ selectedTeacher.getId());
        String growlMsg = "Teacher updated successfully";

        try{
            userService.saveUser(selectedTeacher, 1);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to update Teacher...";
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);

        return MAIN_URL;
    }

}
