package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagesController {

    private final UserServiceImpl userServiceImpl;

    public PagesController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/login")
    public String loginAction(Model model) {
        System.out.println("Inside loginAction()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }

        return "/login";
    }


    @GetMapping("/logout")
    public String logoutAction(Model model){
        System.out.println("Inside logoutAction()");
        return null;
    }

    @GetMapping("/dashboard")
    public String redirectToDashboard(Model model) {
        System.out.println("Inside redirectToDashboard()");
        model.addAttribute("teacherList", userServiceImpl.getAllTeachers());
        model.addAttribute("studentList", userServiceImpl.getAllStudents());
//        model.addAttribute("roleList", roleServiceImpl.getAllRoles());
//        model.addAttribute("courseList", courseServiceImpl.getAllCourses());
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
