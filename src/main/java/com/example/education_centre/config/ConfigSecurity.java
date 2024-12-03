package com.example.education_centre.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Vô hiệu hóa CSRF nếu không dùng
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/register", "/static/**", "/assets/**").permitAll() // Các URL công khai
                        .requestMatchers("/dashboard/**").hasRole("ADMIN") // Chỉ cho ADMIN
                        .requestMatchers("/user/**").hasRole("USER") // Chỉ cho USER
                        .anyRequest().authenticated() // Mọi request khác cần xác thực
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Trang đăng nhập
                        .loginProcessingUrl("/login") // URL xử lý đăng nhập
                        .usernameParameter("email") // Tên trường email
                        .passwordParameter("password") // Tên trường password
                        .successHandler(new CustomAuthenticationSuccessHandler()) // Xử lý sau khi đăng nhập thành công
                        .permitAll() // Cho phép mọi người truy cập trang login
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // URL xử lý đăng xuất
                        .logoutSuccessUrl("/login") // Điều hướng sau khi đăng xuất
                        .permitAll()
                )
                .exceptionHandling((exception) -> exception
                        .accessDeniedPage("/access-denied") // Trang truy cập bị từ chối
                );

        return http.build();
    }
}
