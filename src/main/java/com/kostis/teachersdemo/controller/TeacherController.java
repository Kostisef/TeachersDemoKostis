package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeacherController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final UserRepository userRepository;

    public TeacherController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/addNewTeacher")
    public String addNewTeacher(User teacher){
        userRepository.save(teacher);
        return MAIN_URL;
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam Integer teacherId){
        System.out.println("TeacherId to delete: "+ teacherId);

        User teacherToDelete = getTeacher(teacherId);
        userRepository.delete(teacherToDelete);
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
