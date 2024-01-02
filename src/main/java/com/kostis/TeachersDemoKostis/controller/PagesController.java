package com.kostis.TeachersDemoKostis.controller;

import com.kostis.TeachersDemoKostis.entities.Teacher;
import com.kostis.TeachersDemoKostis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class PagesController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public String index(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "dashboard";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
