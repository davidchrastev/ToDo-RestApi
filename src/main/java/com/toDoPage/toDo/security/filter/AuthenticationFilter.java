package com.toDoPage.toDo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toDoPage.toDo.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println(user.getPassword());
            System.out.println(user.getEmail());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return super.attemptAuthentication(request, response);
    }



}
