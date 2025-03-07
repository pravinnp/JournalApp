package com.dev.JournalApp.controller;


import com.dev.JournalApp.entity.User;
import com.dev.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public ResponseEntity<?> createUser() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return userService.saveNewUser(user);
    }


}
