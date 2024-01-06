package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.StudentCourseAssociation;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.CourseModel;
import com.kostis.teachersdemo.models.StudentModel;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.repo.RoleRepository;
import com.kostis.teachersdemo.repo.StudentCourseAssociationRepository;
import com.kostis.teachersdemo.repo.UserRepository;
import com.kostis.teachersdemo.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final CourseRepository courseRepository;
    private final StudentCourseAssociationRepository studentCourseAssociationRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository, StudentCourseAssociationRepository studentCourseAssociationRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.studentCourseAssociationRepository = studentCourseAssociationRepository;
    }


    /**
     * Find All Teachers
     */
    @Override
    public List<User> getAllTeachers() {
        return userRepository.findByRole_Name("TEACHER");
    }

    /**
     * Find All Students
     */
    @Override
    public List<User> getAllStudents() {
        return userRepository.findByRole_Name("STUDENT");
    }

    /**
     * Find User By Id
     *
     * @param id
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id);
    }


    /**
     * Save User
     *
     * @param user
     */
    @Override
    public void saveUser(User user, Integer roleId) throws Exception {
        User userToUpdate = userRepository.findById(user.getId());

        if (userToUpdate != null){
            userToUpdate.setFirstname(user.getFirstname());
            userToUpdate.setLastname(user.getLastname());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setStartYear(user.getStartYear());

            if (roleId.equals(2)){
                userToUpdate.setSemester(user.getSemester());
            }

            userRepository.save(userToUpdate);
        } else {
            throw new Exception("Null incoming user to update.");
        }
    }


    public void deleteTeacher(User teacher){
        User teacherToDelete = getUserById(teacher.getId());
        for (Course course : teacherToDelete.getTaughtCourses()){
            course.setTeacher(null);
        }
        courseRepository.saveAllAndFlush(teacherToDelete.getTaughtCourses());
        userRepository.delete(teacherToDelete);
    }

    public void deleteStudent(User student){
        User studentToDelete = getUserById(student.getId());

        if (!studentToDelete.getEnrolledCourses().isEmpty()){
            studentCourseAssociationRepository.deleteAll(studentToDelete.getEnrolledCourses());
            studentCourseAssociationRepository.flush();
        }

        userRepository.delete(studentToDelete);
    }

    @Override
    public void createNewUser(User user, Integer roleId) {
        user.setId(null);
        user.setTaughtCourses(Collections.emptyList());
        user.setEnrolledCourses(Collections.emptyList());
        user.setRole(roleRepository.findById(roleId)); // 1: Teacher, 2: Student

        if (roleId.equals(1)){
            user.setSemester(null);
        }

        userRepository.save(user);
    }

    @Override
    public void removeTeachingCourse(User selectedTeacher, Course selectedCourse) {
        System.out.println("Inside UserService.removeTeachingCourse()....");
    }

    @Override
    public StudentModel getStudentModelById(Integer id) {
        User userFound = userRepository.findById(id);
        StudentModel studentModel = null;
        if (userFound!=null && userFound.getRole().getId().equals(2)){
            studentModel = new StudentModel();
            studentModel.setId(userFound.getId());
            studentModel.setFirstname(userFound.getFirstname());
            studentModel.setLastname(userFound.getLastname());
            studentModel.setFullName(studentModel.getLastname()+" "+studentModel.getFirstname());

            studentModel.setCourseModelList(Collections.emptyList());

            List<CourseModel> courseModels = populateCourseModelList(userFound);
            studentModel.setCourseModelList(courseModels);

            populateNotAttendingCourses(studentModel);

            return studentModel;
        }
        return null;
    }

    private static List<CourseModel> populateCourseModelList(User userFound) {
        List<CourseModel> courseModels = new ArrayList<>();
        for (StudentCourseAssociation association: userFound.getEnrolledCourses()){
            CourseModel courseModel = new CourseModel();
            courseModel.setId(association.getCourse().getId());
            courseModel.setName(association.getCourse().getName());
            courseModel.setDescription(association.getCourse().getDescription());
            courseModel.setSemester(association.getCourse().getSemester());
            String teacherFullName = "-";
            if (association.getCourse().getTeacher() != null){
                teacherFullName = association.getCourse().getTeacher().getLastname() + " " + association.getCourse().getTeacher().getFirstname();
            }
            courseModel.setTeacherFullName(teacherFullName);
            courseModels.add(courseModel);
        }
        return courseModels;
    }

    private void populateNotAttendingCourses(StudentModel studentModel){
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
                CourseModel model = new CourseModel();
                model.setId(course.getId());
                model.setName(course.getName());
                model.setDescription(course.getDescription());
                model.setSemester(course.getSemester());
                String teacherFullName = "-";
                if (course.getTeacher() != null){
                    teacherFullName = course.getTeacher().getLastname() + " " + course.getTeacher().getFirstname();
                }
                model.setTeacherFullName(teacherFullName);
                notAttendingCourseModels.add(model);
            }
        }
        studentModel.setNotAttendingCourseModelList(notAttendingCourseModels);
    }
}
