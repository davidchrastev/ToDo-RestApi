//package com.toDoPage.toDo.security;
//
//
//import com.toDoPage.toDo.security.filter.AuthenticationFilter;
//import com.toDoPage.toDo.security.filter.ExceptionHandlerFilter;
//import com.toDoPage.toDo.security.filter.JWTAuthorizationFilter;
//import com.toDoPage.toDo.security.manager.CustomAuthenticationManager;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@AllArgsConstructor
//public class SecurityConfig {
//
//
//    private final CustomAuthenticationManager customAuthenticationManager;
//
//    private BCryptPasswordEncoder passwordEncoder;
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
//        authenticationFilter.setFilterProcessesUrl("/authenticate");
//        http
//                .headers().frameOptions().disable() // New Line: the h2 console runs on a "frame". By default, Spring Security prevents rendering within an iframe. This line disables its prevention.
//                .and()
//                .csrf().disable()
//                .authorizeRequests()// New Line: allows us to access the h2 console without the need to authenticate. ' ** '  instead of ' * ' because multiple path levels will follow /h2.
//                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
//                .addFilter(authenticationFilter)
//                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//
//    }
//
//    @Bean
//    public UserDetailsService users() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin-pass"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user-pass"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
//}
