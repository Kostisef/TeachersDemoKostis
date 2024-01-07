package com.kostis.teachersdemo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseModel {
    private Integer id;
    private String name;
    private String description;
    private Integer semester;
    private String teacherFullName;

    private Integer numOfStudents;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CourseModel{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", semester=").append(semester);
        sb.append(", numOfStudents=").append(numOfStudents);
        sb.append(", teacherFullName='").append(teacherFullName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
