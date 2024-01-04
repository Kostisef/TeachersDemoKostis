package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.repo.RoleRepository;
import com.kostis.teachersdemo.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
