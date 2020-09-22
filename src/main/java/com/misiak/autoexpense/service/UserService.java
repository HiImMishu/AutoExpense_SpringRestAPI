package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.User;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    public User saveUser(Principal principal);

    public User getUser(String id);
}
