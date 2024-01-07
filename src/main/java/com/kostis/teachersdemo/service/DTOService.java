package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.Role;
import com.kostis.teachersdemo.entities.StudentCourseAssociation;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.CourseModel;
import com.kostis.teachersdemo.models.RoleModel;
import com.kostis.teachersdemo.models.StudentModel;
import com.kostis.teachersdemo.models.TeacherModel;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.repo.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOService {

    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;

    public DTOService(RoleRepository roleRepository, CourseRepository courseRepository) {
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Entity: User [Teacher] --> DTO: TeacherModel
     */
    public TeacherModel convertUserToTeacherModel(User teacher){
        TeacherModel model = new TeacherModel();

        model.setId(teacher.getId());
        model.setFirstname(teacher.getFirstname());
        model.setLastname(teacher.getLastname());
        model.setFullName(teacher.getLastname() + " " + teacher.getFirstname());
        model.setUsername(teacher.getUsername());
        model.setStartYear(teacher.getStartYear());
        model.setTeachingCourseModelList(convertCourseListToModelList(teacher.getTaughtCourses()));

        return model;
    }

    /**
     * Entity: User [Student] --> DTO: StudentModel
     */
    public StudentModel convertUserToStudentModel(User student){
        StudentModel model = new StudentModel();

        model.setId(student.getId());
        model.setFirstname(student.getFirstname());
        model.setLastname(student.getLastname());
        model.setFullName(student.getLastname() + " " + student.getFirstname());
        model.setUsername(student.getUsername());
        model.setStartYear(student.getStartYear());
        model.setSemester(student.getSemester());

        model.setCourseModelList(populateCourseModelList(student));
        model.setNotAttendingCourseModelList(populateNotAttendingCourses(model));

        return model;
    }

    private List<CourseModel> populateCourseModelList(User student) {
        List<CourseModel> courseModels = new ArrayList<>();
        for (StudentCourseAssociation association: student.getEnrolledCourses()){
            courseModels.add(convertCourseToModel(association.getCourse()));
        }
        return courseModels;
    }

    private List<CourseModel> populateNotAttendingCourses(StudentModel studentModel){
        List<Course> allCourses = courseRepository.findAll();
        List<CourseModel> notAttendingCourseModels = new ArrayList<>();

        for (Course course: allCourses){
            boolean isAttending = false;
            for (CourseModel courseModel: studentModel.getCourseModelList()){
                if (course.getId().equals(courseModel.getId())){
                    isAttending = true;
                    break;
                }
            }

            if (!isAttending){
                notAttendingCourseModels.add(convertCourseToModel(course));
            }
        }
        return notAttendingCourseModels;
    }

    /**
     * DTO: TeacherModel --> Entity: User [Teacher]
     */
    public User convertTeacherModelToUser(TeacherModel model){
        User user = new User();

        user.setId(model.getId());
        user.setFirstname(model.getFirstname());
        user.setLastname(model.getLastname());
        user.setEmail(model.getEmail());
        user.setUsername(model.getUsername());
        user.setStartYear(model.getStartYear());
        user.setRole(roleRepository.findById(1).orElse(null));

        user.setSemester(null);
        user.setEnrolledCourses(null);
        user.setTaughtCourses(null);
        return user;
    }


    /**
     * DTO: TeacherModel --> Entity: User [Teacher]
     */
    public User convertStudentModelToUser(StudentModel model){
        User user = new User();

        user.setId(model.getId());
        user.setFirstname(model.getFirstname());
        user.setLastname(model.getLastname());
        user.setUsername(model.getUsername());
        user.setEmail(model.getEmail());
        user.setSemester(model.getSemester());
        user.setStartYear(model.getStartYear());
        user.setRole(roleRepository.findById(2).orElse(null));

        user.setEnrolledCourses(null);
        user.setTaughtCourses(null);
        return user;
    }


    /**
     * Entity: Course --> DTO: CourseModel
     */
    public CourseModel convertCourseToModel(Course course){
        CourseModel model = new CourseModel();

        model.setId(course.getId());
        model.setName(course.getName());
        model.setDescription(course.getDescription());
        model.setSemester(course.getSemester());

        if (course.getStudentAssociations()!=null){
            model.setNumOfStudents(course.getStudentAssociations().size());
        } else {
            model.setNumOfStudents(0);
        }


        String teacherFullName = "-";
        if (course.getTeacher() != null){
            teacherFullName = course.getTeacher().getLastname() + " " + course.getTeacher().getFirstname();
        }
        model.setTeacherFullName(teacherFullName);

        return model;
    }

    /**
     * DTO: CourseModel --> Entity: Course
     */
    public Course convertModelToCourse(CourseModel model){
        Course course = new Course();

        course.setId(model.getId());
        course.setName(model.getName());
        course.setDescription(model.getDescription());
        course.setSemester(model.getSemester());

        course.setTeacher(null);
        course.setStudentAssociations(null);

        return course;
    }

    /**
     * Entity: List<Course> --> DTO: List<CourseModel>
     */
    public List<CourseModel> convertCourseListToModelList(List<Course> courseList){
        List<CourseModel> modelList = new ArrayList<>();

        if (courseList != null) {
            for (Course course: courseList){
                modelList.add(convertCourseToModel(course));
            }
        }

        return modelList;
    }


    /**
     * Entity: Role --> DTO: RoleModel
     */
    public RoleModel convertRoleToModel(Role role) {
        return new RoleModel(role.getName(), role.getDescription());
    }

    /**
     * DTO: RoleModel --> Entity: Role
     */
    public Role convertModelToRole(RoleModel model) {
        Role role = new Role();
        role.setName(model.getName());
        role.setDescription(model.getDescription());

        return role;
    }

}
