package com.kostis.teachersdemo.security;

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
        http
            .authorizeRequests()
                .antMatchers("/resources/**", "/global/**","/css/**", "/static/**", "/images/**" ,"/js/**", "/modals/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
            // LOGIN
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/dashboard", true)
                .usernameParameter("email").passwordParameter("password")
                .permitAll()
                .and()
            // LOGOUT
            .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
            // ACCESS DENIED
            .exceptionHandling()
                .accessDeniedPage("/401");


        http.csrf().disable(); // For dev only
        http.headers().frameOptions().disable();
    }
}