package com.kostis.teachersdemo.security;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.TeacherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    public CustomUserDetailsService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = teacherRepository.findByEmail(email);
        if (user == null){
            String msg = "User not found with email: " + email;
            System.out.println(msg);
            throw new UsernameNotFoundException(msg);
        }

        return new CustomUserDetails(user);
    }
}
