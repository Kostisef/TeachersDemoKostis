package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.models.RoleModel;
import com.kostis.teachersdemo.service.impl.RoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RoleController {
    private static final String MAIN_URL = "redirect:/dashboard";

    private final RoleServiceImpl roleService;

    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/getRole")
    @ResponseBody
    public Role getRole(Integer id) {
        return roleService.getRoleById(id);
    }


    @PostMapping("/addNewRole")
    public String addNewRole(RoleModel role, RedirectAttributes redirectAttributes){
        String growlMsg = "Role created successfully";

        try {
            roleService.addNewRole(role);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            growlMsg = "Role not created... Exception message: "+e.getMessage();
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }

        return MAIN_URL;
    }


    @PostMapping("/saveRole")
    public String saveRole(Role selectedRole, RedirectAttributes redirectAttributes) {
        System.out.println("RoleId to update: "+ selectedRole.getId());
        String growlMsg = "Role updated successfully";

        try{
            roleService.saveRole(selectedRole);
            redirectAttributes.addFlashAttribute("successMessage", growlMsg);
        } catch (Exception e){
            System.out.println(e.getMessage());
            growlMsg = "Failed to update Role... Exception message: "+e.getMessage();
            redirectAttributes.addFlashAttribute("errorMessage", growlMsg);
        }


        return MAIN_URL;
    }

    @RequestMapping("/searchRoles")
    @ResponseBody
    public List<Role> searchRoles(String searchValue) {
        return roleService.customSearchRoles(searchValue);
    }
}
