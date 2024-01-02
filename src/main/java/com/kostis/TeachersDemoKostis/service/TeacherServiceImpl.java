package com.kostis.TeachersDemoKostis.service;

import com.kostis.TeachersDemoKostis.entities.Teacher;
import com.kostis.TeachersDemoKostis.repo.TeacherRepository;
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
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * FIND BY ID
     *
     * @param id
     */
    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    /**
     * FIND BY ID
     *
     * @param email
     */
    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    /**
     * Save
     *
     * @param teacher
     */
    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
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
