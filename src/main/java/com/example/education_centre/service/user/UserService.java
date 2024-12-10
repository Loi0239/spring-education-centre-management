package com.example.education_centre.service.user;

import com.example.education_centre.dto.user.CheckUser;
import com.example.education_centre.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<User> findAll(int page, int size);
    CheckUser findById(Long id);
    List<User> findByUserCodes(String userCode);
    List<User> getUsersByPageAndSize(int page, int size);
    List<User> searchUsers(String keyword, int page, int size);
    long getTotalUserCount();
    long getSearchResultCount(String keyword);
    boolean isUserCodeExists(String userCode);
    boolean isEmailExists(String email);
    void save(CheckUser data);
    void delete(long id);
}
