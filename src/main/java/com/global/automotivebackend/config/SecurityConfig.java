//package com.global.automotivebackend.config;
//
//import com.global.automotivebackend.service.CustomUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    CustomUserDetailService customUserDetailService;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailService();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll()
//                .and().authorizeHttpRequests().requestMatchers(AUTH_BLACKLIST).authenticated()
//                .and().formLogin();
//        return http.build();
//    }
//
//    public static final String[] AUTH_WHITELIST = {"/company/all", "/company/get", "/company/historical",
//            "/vehicle/all", "/vehicle/get", "/vehicle/historical",
//            "/device/all", "/device/get", "/device/historical",
//            "/gps/all", "/register"};
//
//    public static final String[] AUTH_BLACKLIST = {"/company/update", "/company/delete", "/company/add",
//            "/vehicle/update", "/vehicle/delete", "/vehicle/add",
//            "/device/update", "/device/delete", "/device/add"};
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//}