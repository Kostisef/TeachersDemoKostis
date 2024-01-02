package com.kostis.TeachersDemoKostis.repo;

import com.kostis.TeachersDemoKostis.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByEmail(String email);
}
