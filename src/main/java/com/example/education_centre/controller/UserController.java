package com.example.education_centre.controller;

import com.example.education_centre.dto.user.CheckUser;
import com.example.education_centre.dto.ValidationGroups;
import com.example.education_centre.model.Role;
import com.example.education_centre.service.role.RoleService;
import com.example.education_centre.service.user.UserService;
import com.example.education_centre.util.CheckAddress;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("")
    public String index(

            Model model
    ){
        model.addAttribute("defaultPage", "/default");
        return "users/index";
    }

    @GetMapping("/add")
    public String addView(Model model){
        List<Role> roles = roleService.findAll();
        model.addAttribute("form", new CheckUser());
        model.addAttribute("roles", roles);
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("userPage", "/user");
        return "users/add";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam("id") long id, Model model){
        CheckUser user =  userService.findById(id);
        List<Role> roles = roleService.findAll();

        String address = user.getAddress();
        String[] checkAddressParts = {};
        if(address != null){
            String[] addressParts = address.split("\\|");
            checkAddressParts = CheckAddress.checkandfilterAddress(addressParts);
            user.setAddress(checkAddressParts[checkAddressParts.length-1]);
        }

        model.addAttribute("form", user);
        model.addAttribute("roles", roles);
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("userPage", "/user");
        model.addAttribute("addressParts", checkAddressParts);
        return "users/update";
    }

    @PostMapping("/save")
    public String save(@Validated(ValidationGroups.OnCreate.class) @ModelAttribute("form") CheckUser user, BindingResult rs, Model model){
        if (user.getIdRole() == -1) {
            rs.rejectValue("idRole", "role.required", "Vui lòng chọn chức vụ.");
        }

        if (!(user.getPassword() != null && user.getPassword().equals(user.getConfirmPassword()))){
            rs.rejectValue("confirmPassword", "confirmPassword.required",
                    "Mật khẩu và xác nhận mật khẩu phải khớp");
        }

        if(rs.hasErrors()){
            System.out.println("có lỗi");
            System.out.println(rs.getAllErrors());
            List<Role> roles = roleService.findAll();
            model.addAttribute("form", user);
            model.addAttribute("roles", roles);
            model.addAttribute("defaultPage", "/default");
            model.addAttribute("userPage", "/user");
            return "users/add";
        }

        try {
            userService.save(user);  // Gọi Service để lưu user
        } catch (IllegalArgumentException e) {
            System.out.println("có lỗi 2");
            if(e.getMessage().contains("Mã người dùng")){
                rs.rejectValue("userCode","userExists", e.getMessage());
            } else if(e.getMessage().contains("Email")){
                rs.rejectValue("email","userExists", e.getMessage());
            }
            model.addAttribute("form", user);
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            model.addAttribute("defaultPage", "/default");
            model.addAttribute("userPage", "/user");
            return "users/add";  // Trả về trang thêm người dùng với lỗi
        }

        return "redirect:/user/add";
    }

    @PostMapping("/edit")
    public String update(@Validated(ValidationGroups.OnUpdate.class) @ModelAttribute("form")
                             CheckUser user, BindingResult rs, Model model){
        if (user.getIdRole() == -1) {
            rs.rejectValue("idRole", "role.required", "Vui lòng chọn chức vụ.");
        }

        if(rs.hasErrors()){
            System.out.println("có lỗi");
            System.out.println("lỗi: " + rs.getAllErrors());
            List<Role> roles = roleService.findAll();
            model.addAttribute("form", user);
            model.addAttribute("roles", roles);
            model.addAttribute("defaultPage", "/default");
            model.addAttribute("userPage", "/user");
            return "users/update";
        }

        System.out.println(user.toString());
        try {
            userService.save(user);  // Gọi Service để lưu user
        } catch (IllegalArgumentException e) {
            System.out.println("có lỗi 2");
            if(e.getMessage().contains("Mã người dùng")){
                rs.rejectValue("userCode","userExists", e.getMessage());
            } else if(e.getMessage().contains("Email")){
                rs.rejectValue("email","userExists", e.getMessage());
            }
            model.addAttribute("form", user);
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            model.addAttribute("defaultPage", "/default");
            model.addAttribute("userPage", "/user");
            return "users/update";  // Trả về trang thêm người dùng với lỗi
        }

        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id")long id){
        userService.delete(id);
        return "redirect:/user";
    }
}
