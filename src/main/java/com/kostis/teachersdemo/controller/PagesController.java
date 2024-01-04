package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.security.CustomUserDetails;
import com.kostis.teachersdemo.service.impl.CourseServiceImpl;
import com.kostis.teachersdemo.service.impl.RoleServiceImpl;
import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final CourseServiceImpl courseService;

    public PagesController(UserServiceImpl userService, RoleServiceImpl roleService, CourseServiceImpl courseService) {
        this.userService = userService;
        this.roleService = roleService;
        this.courseService = courseService;
    }


    @GetMapping("/login")
    public String loginAction(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }

        return "/login";
    }


    @GetMapping("/dashboard")
    public String redirectToDashboard(Model model) {
        System.out.println("Hello from redirectToDashboard()");
        model.addAttribute("teacherList", userService.getAllTeachers());
        model.addAttribute("studentList", userService.getAllStudents());
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("courseList", courseService.getAllCourses());
//        model.addAttribute("successMessage", "Entity saved successfully");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("username", ((CustomUserDetails) authentication.getPrincipal()).getRealUsername());
//        model.addAttribute("roleList", roleServiceImpl.getAllRoles());
//        model.addAttribute("courseList", courseServiceImpl.getAllCourses());
        return "/dashboard";
    }


    @GetMapping("/401")
    public String redirectTo401(Model model) {
        System.out.println("Inside redirectTo401()");
        return "error-401";
    }

    @GetMapping("/404")
    public String redirectTo404(Model model) {
        System.out.println("Inside redirectTo404()");
        return "error-404";
    }

    @GetMapping("/500")
    public String redirectTo500(Model model) {
        System.out.println("Inside redirectTo500()");
        return "error-500";
    }

}
