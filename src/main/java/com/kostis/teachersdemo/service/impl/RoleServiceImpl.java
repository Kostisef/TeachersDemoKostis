package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.models.RoleModel;
import com.kostis.teachersdemo.repo.RoleRepository;
import com.kostis.teachersdemo.service.DTOService;
import com.kostis.teachersdemo.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final DTOService dtoService;

    public RoleServiceImpl(RoleRepository roleRepository, DTOService dtoService) {
        this.roleRepository = roleRepository;
        this.dtoService = dtoService;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void saveRole(Role role) throws Exception {
        Role roleToUpdate = roleRepository.findById(role.getId()).orElse(null);
        System.out.println();
        if (roleToUpdate != null){
            roleToUpdate.setDescription(role.getDescription());
            roleRepository.save(roleToUpdate);
        } else {
            throw new Exception("Null incoming role to update.");
        }
    }

    @Override
    public void addNewRole(RoleModel role) throws Exception {
        if (role != null){
            Role roleToCreate = dtoService.convertModelToRole(role);

            roleRepository.save(roleToCreate);
        } else {
            throw new Exception("Null incoming role to update.");
        }
    }
}
