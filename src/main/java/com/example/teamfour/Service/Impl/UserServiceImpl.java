package com.example.teamfour.Service.Impl;

import com.example.teamfour.Entity.Role;
import com.example.teamfour.Entity.User;
import com.example.teamfour.Repository.UserRepository;
import com.example.teamfour.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setParola(passwordEncoder.encode(user.getParola()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findOne(long id){
        return userRepository.getOne(id);
    }

    public void Delete(User user){
        userRepository.delete(user);
    }
}
