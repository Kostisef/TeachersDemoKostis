package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.repo.RoleRepository;
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

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
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

    /**
     * Find User By Email
     *
     * @param email
     */
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Save User
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Delete User
     *
     * @param id
     */
    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }


    public void deleteTeacher(User teacher){
        for (Course course : teacher.getTaughtCourses()){
            course.setTeacher(null);
        }
        courseRepository.saveAllAndFlush(teacher.getTaughtCourses());
        userRepository.delete(teacher);
    }

    @Override
    public void createNewTeacher(User teacher) {
        teacher.setSemester(null);
        teacher.setTaughtCourses(Collections.emptyList());
        teacher.setEnrolledCourses(Collections.emptyList());
        teacher.setRole(roleRepository.findById(1)); // 1: Teacher


        userRepository.save(teacher);
    }
}
