package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.StudentCourseAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseAssociationRepository extends JpaRepository<StudentCourseAssociation, Integer>{
    StudentCourseAssociation findByStudent_IdAndCourse_Id(Integer studentId, Integer courseId);
}