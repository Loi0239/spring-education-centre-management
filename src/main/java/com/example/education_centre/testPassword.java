package com.example.education_centre;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
