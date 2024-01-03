package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Course, Long> {

}
