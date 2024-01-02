package com.kostis.teachersdemo.security;

import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.repo.TeacherRepository;
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

        User user = teacherRepository.findByEmail(email);
        if (user == null){
            String msg = "User not found with email: " + email;
            System.out.println(msg);
            throw new UsernameNotFoundException(msg);
        }

        System.out.println("User found! "+ user);
        return new CustomUserDetails(user);
    }
}
