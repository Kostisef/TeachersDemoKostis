//package com.kostis.teachersdemo.entities;
//
//import javax.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "TEACHER_COURSE_ASSOCIATION")
//public class TeacherCourseAssociation implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID", nullable = false)
//    private User teacher;
//
//    @ManyToOne
//    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID", nullable = false)
//    private Course course;
//
//
//}