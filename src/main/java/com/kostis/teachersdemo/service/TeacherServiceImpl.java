package com.kostis.teachersdemo.service;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    /**
     * FIND ALL
     */
    @Override
    public List<User> getAllTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * FIND BY ID
     *
     * @param id
     */
    @Override
    public User getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    /**
     * FIND BY ID
     *
     * @param email
     */
    @Override
    public User getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    /**
     * Save
     *
     * @param user
     */
    @Override
    public void saveTeacher(User user) {
        teacherRepository.save(user);
    }

    /**
     * Delete
     *
     * @param id
     */
    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
