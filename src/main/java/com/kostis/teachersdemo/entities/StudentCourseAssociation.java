package com.kostis.teachersdemo.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDENT_COURSE_ASSOCIATION")
public class StudentCourseAssociation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID", nullable = false)
    private Course course;


}