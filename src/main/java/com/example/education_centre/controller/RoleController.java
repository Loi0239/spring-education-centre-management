package com.example.education_centre.controller;

import com.example.education_centre.dto.ValidationGroups;
import com.example.education_centre.dto.role.CheckRole;
import com.example.education_centre.model.GroupPermission;
import com.example.education_centre.model.PermissionRole;
import com.example.education_centre.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("rolePage", "/role");
        return "roles/index";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam("id") int id, Model model){
        CheckRole role =  roleService.findByIdCheckRole(id);
        List<GroupPermission> groupPermissions = roleService.getAllGroupPermissions();
        List<PermissionRole> getPermissionRoles = roleService.getPermissionRolesByRoleId(id);

        List<Integer> permissionRoles = getPermissionRoles.stream()
                .map(permissionRole -> permissionRole.getPermission().getId()) // Truy cập permissionId
                .collect(Collectors.toList());

        if(permissionRoles.isEmpty()){
            permissionRoles.add(-1);
        }

        System.out.println(groupPermissions);
        System.out.println(permissionRoles);

        model.addAttribute("groupPermissions", groupPermissions);
        model.addAttribute("permissionRoles", permissionRoles);
        model.addAttribute("form", role);
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("rolePage", "/role");
        return "roles/update";
    }

    @PostMapping("edit")
    public String update(@Validated(ValidationGroups.OnUpdate.class)
                         @ModelAttribute("form")CheckRole role, BindingResult rs,
                         @RequestParam List<Integer> permissions, Model model){
        if(rs.hasErrors()){
            System.out.println("có lỗi");
            System.out.println("lỗi: " + rs.getAllErrors());
            model.addAttribute("form", role);
            model.addAttribute("defaultPage", "/default");
            model.addAttribute("rolePage", "/role");
            return "roles/update";
        }

        roleService.updateRolePermissions(role.getId(), permissions);
        roleService.save(role);

        return "redirect:/role";
    }
}
