package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/add")
    public String showAddUserForm() {
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String showDeleteUserForm() {
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Integer id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String showUpdateUserForm() {
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam Integer id, @ModelAttribute User user) {
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }

}
