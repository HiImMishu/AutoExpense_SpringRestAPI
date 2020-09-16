package com.misiak.autoexpense.repository;

import com.misiak.autoexpense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
