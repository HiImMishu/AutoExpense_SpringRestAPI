package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.User;
import com.misiak.autoexpense.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/sign-in")
    public User signIn(Principal principal) {
        return userService.saveUser(principal);
    }

    @GetMapping()
    public User getUser(Principal principal) {
        return userService.getUser(principal.getName());
    }
}
