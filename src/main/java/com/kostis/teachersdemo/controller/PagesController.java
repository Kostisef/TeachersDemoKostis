package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.service.TeacherService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagesController {

    private final TeacherService teacherService;

    public PagesController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/login")
    public String showLogin(Model model) {
        System.out.println("Inside showLogin()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }

        return "/login";
    }

    @GetMapping("/dashboard")
    public String redirectToDashboard(Model model) {
        System.out.println("Inside redirectToDashboard()");
        model.addAttribute("teacherList", teacherService.getAllTeachers());
        return "/dashboard";
    }


    @GetMapping("/401")
    public String redirectTo401(Model model) {
        System.out.println("Inside redirectTo401()");
        return "/401";
    }

    @GetMapping("/404")
    public String redirectTo404(Model model) {
        System.out.println("Inside redirectTo404()");
        return "/404";
    }

    @GetMapping("/500")
    public String redirectTo500(Model model) {
        System.out.println("Inside redirectTo500()");
        return "/500";
    }

}
