package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.TeacherModel;
import com.kostis.teachersdemo.service.impl.DTOServiceImpl;
import com.kostis.teachersdemo.service.impl.CourseServiceImpl;
import com.kostis.teachersdemo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class TeacherController {

    private static final String MAIN_URL = "redirect:/dashboard";

    private final UserServiceImpl userService;
    private final CourseServiceImpl courseService;

    private final DTOServiceImpl dtoService;


    public TeacherController(UserServiceImpl userService, CourseServiceImpl courseService, DTOServiceImpl dtoService) {
        this.userService = userService;
        this.courseService = courseService;
        this.dtoService = dtoService;
    }

    @RequestMapping("/getTeacher")
    @ResponseBody
    public User getTeacher(Integer id) {
        User teacherFound = userService.getUserById(id);
        return teacherFound;
    }

    @RequestMapping("/getTeacherModel")
    @ResponseBody
    public TeacherModel getTeacherModel(Integer id) {
        User teacherFound = userService.getUserById(id);

        return dtoService.convertUserToTeacherModel(teacherFound);
    }


    @PostMapping("/addNewTeacher")
    public String addNewTeacher(TeacherModel teacher, @RequestParam("password") String rawPassword, RedirectAttributes redirectAttributes){

        String growlMsg = "Teacher created successfully";
        try{
            userService.createNewTeacher(teacher, rawPassword);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to create teacher... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(TeacherModel teacherIncoming, RedirectAttributes redirectAttributes){
        System.out.println("TeacherId to delete: "+ teacherIncoming.getId());
        String growlMsg = "Teacher deleted successfully";
        try{
            userService.deleteTeacher(teacherIncoming);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            growlMsg = "Failed to delete teacher... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }


    @PostMapping("/saveTeacher")
    public String saveTeacher(TeacherModel selectedTeacher, RedirectAttributes redirectAttributes) {
        System.out.println("TeacherId to update: "+ selectedTeacher.getId());
        String growlMsg = "Teacher updated successfully";

        try{
            userService.saveTeacher(selectedTeacher);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to update Teacher... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }

    @PostMapping("/removeTeachingCourse")
    public String removeTeachingCourse(@RequestParam("selectedTeacher.id") Integer selectedTeacherId,
                                     @RequestParam("selectedCourse.id") Integer selectedCourseId,
                                     RedirectAttributes redirectAttributes) {
        System.out.println("TeacherId to use: "+ selectedTeacherId);
        System.out.println("CourseId to use: "+ selectedCourseId);
        String growlMsg = "Course removed from Teacher successfully";

        try{
            courseService.removeTeacherFromCourse(selectedTeacherId, selectedCourseId);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to remove course from Teacher... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }


        return MAIN_URL;
    }

    @PostMapping("/addTeachingCourse")
    public String addTeachingCourse(@RequestParam("selectedTeacher.id") Integer selectedTeacherId,
                                       @RequestParam("selectedCourse.id") Integer selectedCourseId,
                                       RedirectAttributes redirectAttributes) {
        System.out.println("TeacherId to use: "+ selectedTeacherId);
        System.out.println("CourseId to use: "+ selectedCourseId);
        String growlMsg = "Course added to Teacher successfully";

        try{
            courseService.addTeacherToCourse(selectedTeacherId, selectedCourseId);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to remove course from Teacher... Exception message: '"+e.getMessage()+"'";
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }


    @RequestMapping("/searchTeachers")
    @ResponseBody
    public List<TeacherModel> searchTeacherModels(String searchValue) {
        return userService.customSearchTeachers(searchValue);
    }

}
