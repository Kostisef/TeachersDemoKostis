package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.models.RoleModel;

import java.util.List;

public interface IRoleService {

    List<RoleModel> getAllRoleModels();

    RoleModel getRoleModelById(Integer id);

    void saveRole(RoleModel role) throws Exception;

    void addNewRole(RoleModel role) throws Exception;

    List<RoleModel> customSearchRoles(String searchValue);
}
