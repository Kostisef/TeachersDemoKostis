package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final UserRepository userRepository;

    public StudentController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/addNewStudent")
    public String addNewStudent(User student){
        userRepository.save(student);

        return MAIN_URL;
    }

    @PostMapping("/deleteStudent")
    public String deleteTeacher(@RequestParam Integer studentId){
        System.out.println("StudentId to delete: "+ studentId);

        User studentToDelete = getStudent(studentId);
        userRepository.delete(studentToDelete);

        return MAIN_URL;
    }

    @RequestMapping("/getStudent")
    @ResponseBody
    public User getStudent(Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping("/saveStudent")
    public String saveStudent(User selectedStudent) {

        User studentToUpdate = userRepository.findById(selectedStudent.getId());

        if (studentToUpdate !=null){
            // Convert model to entity!
            studentToUpdate.setFirstname(selectedStudent.getFirstname());
            studentToUpdate.setLastname(selectedStudent.getLastname());
            studentToUpdate.setUsername(selectedStudent.getUsername());
            studentToUpdate.setEmail(selectedStudent.getEmail());
            studentToUpdate.setStartYear(selectedStudent.getStartYear());
            studentToUpdate.setSemester(selectedStudent.getSemester());

            // Save the edited student to the database
            userRepository.save(studentToUpdate);
        }

        return MAIN_URL;
    }

}
