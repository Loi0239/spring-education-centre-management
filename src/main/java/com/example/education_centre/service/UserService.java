package com.example.education_centre.service;

import com.example.education_centre.model.User;

public interface UserService {
    User findByEmail(String email);
}
