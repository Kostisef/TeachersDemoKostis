package com.kostis.teachersdemo.controller;

import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.repo.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {
    private static final String MAIN_URL = "redirect:/dashboard";

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @PostMapping("/addNewRole")
    public String addNewRole(Role role){
        roleRepository.save(role);

        return MAIN_URL;
    }

    @PostMapping("/deleteRole")
    public String deleteRole(@RequestParam Integer roleId){
        System.out.println("RoleId to delete: "+ roleId);

        Role roleToDelete = getRole(roleId);
        roleRepository.delete(roleToDelete);

        return MAIN_URL;
    }

    @RequestMapping("/getRole")
    @ResponseBody
    public Role getRole(Integer id) {
        return roleRepository.findById(id);
    }

    @PostMapping("/saveRole")
    public String saveRole(Role selectedRole) {

        Role roleToUpdate = roleRepository.findById(selectedRole.getId());

        if (roleToUpdate !=null){
            // Convert model to entity!
//            roleToUpdate.setFirstname(selectedRole.getFirstname());
//            roleToUpdate.setLastname(selectedRole.getLastname());
//            roleToUpdate.setUsername(selectedRole.getUsername());
//            roleToUpdate.setEmail(selectedRole.getEmail());
//            roleToUpdate.setStartYear(selectedRole.getStartYear());
//            roleToUpdate.setSemester(selectedRole.getSemester());

            // Save the edited student to the database
            roleRepository.save(roleToUpdate);
        }

        return MAIN_URL;
    }
}
