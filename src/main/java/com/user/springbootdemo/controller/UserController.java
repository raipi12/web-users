package com.user.springbootdemo.controller;

import com.user.springbootdemo.model.User;
import com.user.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/save")
    public String createUserForm(User user){
        return "user-create";
    }
    @PostMapping("/save")
    public String saveUser(User user){
        if (user.getFirstName().equals("") || user.getLastName().equals(""))
            return "user-create";

        userService.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }
    @PostMapping("/update")
    public String updateUser(User user){
        if (user.getFirstName().equals("") || user.getLastName().equals(""))
            return "user-update";

        userService.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }
}
