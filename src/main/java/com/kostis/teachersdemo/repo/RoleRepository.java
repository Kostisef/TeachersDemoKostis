package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Role findById(Integer id);

    Role findByName(String roleName);
}
