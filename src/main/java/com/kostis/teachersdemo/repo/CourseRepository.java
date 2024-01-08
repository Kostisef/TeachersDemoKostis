package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE " +
            "c.name LIKE ?1 OR c.description LIKE ?1 OR CAST(c.semester AS string) LIKE ?1 OR CONCAT(c.teacher.lastname, ' ',c.teacher.firstname) LIKE ?1 " +
            "ORDER BY c.id")
    List<Course> findCoursesWithCustomSearch(String searchValue);
}
