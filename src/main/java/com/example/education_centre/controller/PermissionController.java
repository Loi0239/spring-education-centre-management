package com.example.education_centre.controller;

import com.example.education_centre.dto.ValidationGroups;
import com.example.education_centre.dto.permission.CheckPermission;
import com.example.education_centre.dto.role.CheckRole;
import com.example.education_centre.model.GroupPermission;
import com.example.education_centre.model.Permission;
import com.example.education_centre.service.group_permission.GroupPermissionService;
import com.example.education_centre.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private GroupPermissionService groupPermissionService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("permissionPage", "/permission");
        return "permissions/index";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam("id") int id, Model model){
        CheckPermission permission =  permissionService.findByIdCheckPermission(id);
        List<GroupPermission> groupPermissions = groupPermissionService.findAll();

        model.addAttribute("form", permission);
        model.addAttribute("groupPermissions", groupPermissions);
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("permissionPage", "/permission");
        return "permissions/update";
    }

    @PostMapping("edit")
    public String update(@Validated(ValidationGroups.OnUpdate.class)
                         @ModelAttribute("form") CheckPermission permission, BindingResult rs, Model model){
        if(rs.hasErrors()){
            System.out.println("có lỗi");
            System.out.println("lỗi: " + rs.getAllErrors());
            model.addAttribute("form", permission);
            model.addAttribute("defaultPage", "/default");
            model.addAttribute("permissionPage", "/permission");
            return "permissions/update";
        }

        permissionService.save(permission);

        return "redirect:/permission";
    }
}
