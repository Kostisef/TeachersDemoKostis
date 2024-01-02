package com.kostis.TeachersDemoKostis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/public/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/dashboard", true)
//                .failureUrl("/login?error") // You can customize the error URL
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .and()
//            .exceptionHandling()
//                .accessDeniedPage("/error/404")
//                .and()
//            .csrf().disable(); // For simplicity, you can disable CSRF, but it's not recommended in a production environment



        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email") // Specify the parameter names used in the login form
                .passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .headers()
                .frameOptions()
                .disable()
                .and()
            .csrf().disable();
    }
}