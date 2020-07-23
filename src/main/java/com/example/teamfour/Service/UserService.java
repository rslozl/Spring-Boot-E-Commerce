package com.example.teamfour.Service;

import com.example.teamfour.Entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
