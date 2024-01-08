package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.name LIKE ?1 OR r.description LIKE ?1 ORDER BY r.id")
    List<Role> findRolesWithCustomSearch(String searchValue);
}
