package com.kostis.teachersdemo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String welcomeMsg = ((CustomUserDetails) authentication.getPrincipal()).getUserFullNameMsg();
        request.getSession().setAttribute("welcomeMsg", welcomeMsg);

        response.sendRedirect("dashboard");
    }
}