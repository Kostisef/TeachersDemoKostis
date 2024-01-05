package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.repo.RoleRepository;
import com.kostis.teachersdemo.repo.StudentCourseAssociationRepository;
import com.kostis.teachersdemo.repo.UserRepository;
import com.kostis.teachersdemo.service.IUserService;
import org.springframework.stereotype.Service;

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
}
