package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TeacherController {

    private final UserRepository userRepository;

    public TeacherController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/addNewTeacher")
    public String addNewTeacher(User teacher){
        userRepository.save(teacher);
        return "redirect:/dashboard";
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam Integer teacherId){
        System.out.println("TeacherId to delete: "+ teacherId);
        User teacherToDelete = getTeacher(teacherId);
        userRepository.delete(teacherToDelete);
        return "redirect:/dashboard";
    }

    @RequestMapping("/getTeacher")
    @ResponseBody
    public User getTeacher(Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(User selectedTeacher) {
        // Perform validation if needed
        User existingTeacher = userRepository.findById(selectedTeacher.getId());

        if (existingTeacher !=null){
            // Convert model to entity!
            existingTeacher.setFirstname(selectedTeacher.getFirstname());
            existingTeacher.setLastname(selectedTeacher.getLastname());

            // Save the edited teacher to the database
            userRepository.save(existingTeacher);
        }

        return "redirect:/dashboard";
    }

}
