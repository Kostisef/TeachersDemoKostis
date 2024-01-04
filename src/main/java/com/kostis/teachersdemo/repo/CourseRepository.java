package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findById(Integer id);
}
