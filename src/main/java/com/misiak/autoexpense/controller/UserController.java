package com.misiak.autoexpense.controller;

import com.misiak.autoexpense.entity.User;
import com.misiak.autoexpense.exception.UserNotFoundException;
import com.misiak.autoexpense.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/signIn")
    public User signIn(Principal principal) {
        return userService.saveUser(principal);
    }

    @GetMapping()
    public User getUser(Principal principal) {
        Optional<User> user =  userService.getUser(principal.getName());

        if(user.isPresent())
            return user.get();
        else
            throw new UserNotFoundException(principal.getName());
    }
}
