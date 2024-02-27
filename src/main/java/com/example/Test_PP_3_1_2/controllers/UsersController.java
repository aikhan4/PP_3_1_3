package com.example.Test_PP_3_1_2.controllers;

import com.example.Test_PP_3_1_2.models.Role;
import com.example.Test_PP_3_1_2.models.User;
import com.example.Test_PP_3_1_2.security.UserWrapper;
import com.example.Test_PP_3_1_2.service.RoleService;
import com.example.Test_PP_3_1_2.service.UserService;
import com.example.Test_PP_3_1_2.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {
    @Autowired
    private ApplicationContext context;

    @GetMapping(value = "/admin")
    public String users(Model model) {
        model.addAttribute("usersList", context.getBean("userService", UserService.class).findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserWrapper) authentication.getPrincipal()).getUser();
        model.addAttribute("cookieUser", user);
        return "admin";
    }
    @GetMapping(value = "/add")
    public String addUserPage(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        model.addAttribute("bindingResult", bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User cookieUser = ((UserWrapper) authentication.getPrincipal()).getUser();
        model.addAttribute("cookieUser", cookieUser);
        return "addUser";
    }
    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, @RequestParam String role) {

        context.getBean("userValidator", UserValidator.class).validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User cookieUser = ((UserWrapper) authentication.getPrincipal()).getUser();
            model.addAttribute("cookieUser", cookieUser);
            model.addAttribute("user", user);
            return "addUser";
        }

        if (role.equals("ADMIN")) {
            Role adminRole = context.getBean("roleService", RoleService.class).findById((long) 1).get();
            Role userRole = context.getBean("roleService", RoleService.class).findById((long) 2).get();
            user.addRole(adminRole);
            user.addRole(userRole);
            adminRole.addUser(user);
            userRole.addUser(user);
        } else if (role.equals("USER")) {
            Role userRole = context.getBean("roleService", RoleService.class).findById((long) 2).get();
            user.addRole(userRole);
            userRole.addUser(user);
        } else if (role.isEmpty()) {
            Role userRole = context.getBean("roleService", RoleService.class).findById((long) 2).get();
            user.addRole(userRole);
            userRole.addUser(user);
        }

        context.getBean("userService", UserService.class).save(user);

        return "redirect:/admin";
    }
    @GetMapping(value = "/change")
    public String changeUserPage(@ModelAttribute User user, @RequestParam Long id, Model model, BindingResult bindingResult) {
        model.addAttribute("user", context.getBean("userService", UserService.class).findById(id).get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User cookieUser = ((UserWrapper) authentication.getPrincipal()).getUser();
        model.addAttribute("cookieUser", cookieUser);
        model.addAttribute("bindingResult", bindingResult);
        return "changeUser";
    }

    @PatchMapping(value = "/change")
    public String changeUser(@ModelAttribute User user, Model model, @RequestParam String role, BindingResult bindingResult) {

        context.getBean("userValidator", UserValidator.class).validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User cookieUser = ((UserWrapper) authentication.getPrincipal()).getUser();
            model.addAttribute("cookieUser", cookieUser);
            model.addAttribute("user", user);
            return "changeUser";
        }

        if (role.equals("ADMIN")) {
            Role adminRole = context.getBean("roleService", RoleService.class).findById((long) 1).get();
            Role userRole = context.getBean("roleService", RoleService.class).findById((long) 2).get();
            user.addRole(adminRole);
            user.addRole(userRole);
            adminRole.addUser(user);
            userRole.addUser(user);
        } else if (role.equals("USER")) {
            Role userRole = context.getBean("roleService", RoleService.class).findById((long) 2).get();
            user.addRole(userRole);
            userRole.addUser(user);
        } else if (role.isEmpty()) {
            Role userRole = context.getBean("roleService", RoleService.class).findById((long) 2).get();
            user.addRole(userRole);
            userRole.addUser(user);
        }

        context.getBean("userService", UserService.class).save(user);

        return "redirect:/admin";
    }
    @DeleteMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id) {
        context.getBean("userService", UserService.class).deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/user")
    public String userPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserWrapper) authentication.getPrincipal()).getUser();
        model.addAttribute("user", user);

        return "userInfo";
    }
    @GetMapping(value = "/AdminInfo")
    public String adminInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserWrapper) authentication.getPrincipal()).getUser();
        model.addAttribute("user", user);

        return "adminInfo";
    }
}
