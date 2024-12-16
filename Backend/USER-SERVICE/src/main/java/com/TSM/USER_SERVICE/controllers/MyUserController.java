package com.TSM.USER_SERVICE.controllers;

import com.TSM.USER_SERVICE.payloads.LoginForm;
import com.TSM.USER_SERVICE.repos.MyUserRepo;
import com.TSM.USER_SERVICE.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.TSM.USER_SERVICE.entities.MyUser;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping
public class MyUserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService myUserDetailsService;
    @Autowired
    MyUserRepo myUserRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String handleWelcome() {
        return "Welcome to MyUserHome!";
    }

    @GetMapping("/api/profile")
    public MyUser getProfile(@RequestHeader("Authorization") String authHeader){
        String jwt=authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);
        Optional<MyUser> user = myUserRepo.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @GetMapping("/api/profiles")
    public List<MyUser> getAllProfiles(){
        return myUserRepo.findAll();
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginForm.username()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
    @PostMapping("/register")
    public MyUser createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepo.save(user);
    }

}
