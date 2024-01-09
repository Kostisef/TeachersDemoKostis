package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.models.RoleModel;
import com.kostis.teachersdemo.repo.RoleRepository;
import com.kostis.teachersdemo.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final DTOServiceImpl dtoService;

    public RoleServiceImpl(RoleRepository roleRepository, DTOServiceImpl dtoService) {
        this.roleRepository = roleRepository;
        this.dtoService = dtoService;
    }

    @Override
    public List<RoleModel> getAllRoleModels() {
        List<Role> roleList = roleRepository.findAll();

        List<RoleModel> roleModels = new ArrayList<>();
        for (Role role: roleList){
            roleModels.add(dtoService.convertRoleToModel(role));
        }
        return roleModels;
    }


    @Override
    public RoleModel getRoleModelById(Integer id) {
        Role roleFound = roleRepository.findById(id).orElse(null);

        RoleModel roleModel = null;
        if (roleFound != null){
            roleModel = dtoService.convertRoleToModel(roleFound);
        }

        return roleModel;
    }

    @Override
    public void saveRole(RoleModel roleModel) throws Exception {
        Role roleToUpdate = roleRepository.findById(roleModel.getId()).orElse(null);
        System.out.println();
        if (roleToUpdate != null){
            roleToUpdate.setDescription(roleModel.getDescription());
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

    @Override
    public List<RoleModel> customSearchRoles(String searchValue) {
        searchValue = "%" + searchValue + "%";
        List<Role> roleList = roleRepository.findRolesWithCustomSearch(searchValue);

        List<RoleModel> roleModelList = new ArrayList<>();
        for (Role role: roleList){
            roleModelList.add(dtoService.convertRoleToModel(role));
        }

        return roleModelList;
    }
}
