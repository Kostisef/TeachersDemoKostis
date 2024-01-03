package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.UserRepository;
import com.kostis.teachersdemo.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
