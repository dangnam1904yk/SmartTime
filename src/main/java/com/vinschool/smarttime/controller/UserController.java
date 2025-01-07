package com.vinschool.smarttime.controller;

import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.service.RoleService;
import com.vinschool.smarttime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/user")
    public String getAll(Model model) {

        model.addAttribute("users", userService.getAll());
        return "page/user/list";
    }

    @GetMapping("/user/save{id}")
    public String Form(Model model, @RequestParam(name = "id", required = false) String id) {
        User user = null;
        String type = "Thêm mới tài khoản";
        if (id == null) {
            user = new User();
        } else {

            type = "Sửa thông tin tài khoản";
            user = userService.findById(id);
        }
        model.addAttribute("type", type);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAll());
        return "page/user/save";
    }

    @PostMapping("/user/save")
    public String save(@ModelAttribute("user") User user, Model model, @RequestParam(name = "role") String role) {
        userService.save(user, role);
        return "redirect:/user";
    }
}
