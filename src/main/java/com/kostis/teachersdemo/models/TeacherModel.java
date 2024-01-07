package com.kostis.teachersdemo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherModel {
    private Integer id;
    private String firstname;
    private String lastname;
    private String fullName;
    private String username;
    private String email;

    private Integer startYear;

    private List<CourseModel> teachingCourseModelList;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TeacherModel{");
        sb.append("id=").append(id);
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", startYear=").append(startYear);
        sb.append(", teachingCourseModelList=").append(teachingCourseModelList);
        sb.append('}');
        return sb.toString();
    }
}
