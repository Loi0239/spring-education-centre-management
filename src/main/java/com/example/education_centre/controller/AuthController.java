package com.example.education_centre.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {
    @GetMapping("/login")
    public String loginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Kiểm tra nếu người dùng đã đăng nhập và không phải là AnonymousAuthenticationToken
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {

            var authorities = authentication.getAuthorities();
            if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                // Nếu role là ADMIN, chuyển hướng đến trang quản lý
                return "redirect:/dashboard";
            } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                // Nếu role là USER, chuyển hướng đến trang index
                return "redirect:/user/home";
            }
            // Nếu role không xác định, chuyển hướng đến trang mặc định
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/default")
    public String index(){
        return "index";
    }
}
