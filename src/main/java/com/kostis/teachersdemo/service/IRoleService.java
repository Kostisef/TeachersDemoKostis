package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.Role;

import java.util.List;

public interface IRoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleById(Integer id);

    void saveRole(Role role) throws Exception;

}
