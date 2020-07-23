package com.example.teamfour.Controller;

import com.example.teamfour.Entity.Urunler;
import com.example.teamfour.Entity.User;
import com.example.teamfour.Service.Impl.UserServiceImpl;
import com.example.teamfour.Service.Security.JwtTokenProvider;
import com.example.teamfour.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/api/user/register")
    public void register(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null){
            //This should be ok http status because here will be logout path.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(tokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/api/user/getAllUser")
    public ResponseEntity<?> getAllUser() {
        User user = new User();
        List<User> list =(List<User>) userServiceImpl.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
