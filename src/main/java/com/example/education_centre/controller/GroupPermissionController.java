package com.example.education_centre.controller;

import com.example.education_centre.dto.ValidationGroups;
import com.example.education_centre.dto.group_permission.CheckGroupPermission;
import com.example.education_centre.service.group_permission.GroupPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/group-permission")
public class GroupPermissionController {
    @Autowired
    private GroupPermissionService groupPermissionService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("groupPermissionPage", "/group-permission");
        return "group_permissions/index";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam("id") int id, Model model){
        CheckGroupPermission groupPermission =  groupPermissionService.findByIdCheckGroupPermission(id);

        model.addAttribute("form", groupPermission);
        model.addAttribute("defaultPage", "/default");
        model.addAttribute("groupPermissionPage", "/group-permission");
        return "group_permissions/update";
    }

    @PostMapping("edit")
    public String update(@Validated(ValidationGroups.OnUpdate.class)
                         @ModelAttribute("form")CheckGroupPermission groupPermission, BindingResult rs, Model model){
        if(rs.hasErrors()){
            System.out.println("có lỗi");
            System.out.println("lỗi: " + rs.getAllErrors());
            model.addAttribute("form", groupPermission);
            model.addAttribute("defaultPage", "/default");
            model.addAttribute("groupPermissionPage", "/group-permission");
            return "group_permissions/update";
        }

        groupPermissionService.save(groupPermission);

        return "redirect:/group-permission";
    }
}
