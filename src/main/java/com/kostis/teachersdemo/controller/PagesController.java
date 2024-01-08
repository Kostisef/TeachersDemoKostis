package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.service.impl.CourseServiceImpl;
import com.kostis.teachersdemo.service.impl.RoleServiceImpl;
import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;

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
    public String loginAction(RedirectAttributes redirectAttributes) {
        System.out.println("Inside here!!!!");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && !(authentication instanceof AnonymousAuthenticationToken)) {

            return "redirect:/dashboard";
        }

        return "/login";
    }


    @GetMapping("/dashboard")
    public String redirectToDashboard(Model model, HttpSession session) {
        String welcomeMsg = (String) session.getAttribute("welcomeMsg");
        if (welcomeMsg!=null){
            model.addAttribute("welcomeMsg", welcomeMsg);
            session.removeAttribute("welcomeMsg");
        }
        model.addAttribute("teacherList", userService.getAllTeacherModels());
        model.addAttribute("teachingCoursesList", Collections.emptyList());

        model.addAttribute("studentList", userService.getAllStudentModels());
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("courseList", courseService.getAllCourseModels());
        model.addAttribute("coursesWithoutTeacherList", courseService.getAllCoursesWithoutTeacher());

        return "/dashboard";
    }


    @GetMapping("/401")
    public String redirectTo401(Model model) {
        return "error-401";
    }

    @GetMapping("/404")
    public String redirectTo404(Model model) {
        return "error-404";
    }

    @GetMapping("/500")
    public String redirectTo500(Model model) {
        return "error-500";
    }

}
