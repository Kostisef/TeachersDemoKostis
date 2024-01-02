package com.kostis.TeachersDemoKostis.security;

import com.kostis.TeachersDemoKostis.entities.Teacher;
import com.kostis.TeachersDemoKostis.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Inside custom loadUserByUsername");
        Teacher teacher = teacherRepository.findByEmail(email);
        if (teacher == null){
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return teacher;
    }
}
