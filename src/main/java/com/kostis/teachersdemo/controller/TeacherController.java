package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.UserRepository;
import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeacherController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public TeacherController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @PostMapping("/addNewTeacher")
    public String addNewTeacher(User teacher, RedirectAttributes redirectAttributes){

        String growlMsg = "Teacher created successfully";
        try{
            userService.createNewTeacher(teacher);

        } catch (Exception e){
            growlMsg = "Teacher not created...";
            System.out.println(e.getMessage());
        }

        redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        return MAIN_URL;
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam Integer teacherId, RedirectAttributes redirectAttributes){
        System.out.println("TeacherId to delete: "+ teacherId);
        String growlMsg = "Teacher deleted successfully";
        try{
            User teacherToDelete = getTeacher(teacherId);
            userService.deleteTeacher(teacherToDelete);

        } catch (Exception e){
            growlMsg = "Failed to delete teacher...";
        }


        redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        return MAIN_URL;
    }

    @RequestMapping("/getTeacher")
    @ResponseBody
    public User getTeacher(Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(User selectedTeacher, RedirectAttributes redirectAttributes) {
        // Perform validation if needed
        User teacherToUpdate = userRepository.findById(selectedTeacher.getId());

        if (teacherToUpdate !=null){
            // Convert model to entity!
            teacherToUpdate.setFirstname(selectedTeacher.getFirstname());
            teacherToUpdate.setLastname(selectedTeacher.getLastname());
            teacherToUpdate.setUsername(selectedTeacher.getUsername());
            teacherToUpdate.setEmail(selectedTeacher.getEmail());
            teacherToUpdate.setStartYear(selectedTeacher.getStartYear());

            // Save the edited teacher to the database
            userRepository.save(teacherToUpdate);
        }

        redirectAttributes.addFlashAttribute("successMessage", "Teacher saved successfully");

        return MAIN_URL;
    }

}
