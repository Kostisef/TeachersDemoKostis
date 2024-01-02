package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
