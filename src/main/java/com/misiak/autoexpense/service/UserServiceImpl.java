package com.misiak.autoexpense.service;

import com.misiak.autoexpense.entity.User;
import com.misiak.autoexpense.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    @Override
    public User saveUser(Principal principal) {
        User user = getUserFromToken(principal);

        repository.save(user);

        return user;
    }

    @Override
    public Optional<User> getUser(String id) {
        return repository.findById(id);
    }

    private User getUserFromToken(Principal principal) {
        Map<String, Object> userClaims = ((JwtAuthenticationToken) principal).getTokenAttributes();

        String userId = userClaims.get("sub").toString();
        String email = userClaims.get("email").toString();
        String firstName = userClaims.get("given_name").toString();
        String lastName = userClaims.get("family_name").toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        return new User(userId, email, firstName, lastName, createdAt);
    }
}
