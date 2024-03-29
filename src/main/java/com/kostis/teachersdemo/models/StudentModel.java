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
public class StudentModel {
    private Integer id;
    private String firstname;
    private String lastname;
    private String fullName;
    private String username;
    private String email;

    private Integer semester;
    private Integer startYear;

    private List<CourseModel> courseModelList;
    private List<CourseModel> notAttendingCourseModelList;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StudentModel{");
        sb.append("id=").append(id);
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", semester='").append(semester).append('\'');
        sb.append(", startYear='").append(startYear).append('\'');
        sb.append(", courseModelList=").append(courseModelList);
        sb.append(", notAttendingCourseModelList=").append(notAttendingCourseModelList);
        sb.append('}');
        return sb.toString();
    }
}
