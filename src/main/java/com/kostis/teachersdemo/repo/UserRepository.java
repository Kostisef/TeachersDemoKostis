package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findByRole_Name(String name);


}
