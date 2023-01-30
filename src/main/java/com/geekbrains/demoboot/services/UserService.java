package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {
    User findOneByUsername(String username);
}
