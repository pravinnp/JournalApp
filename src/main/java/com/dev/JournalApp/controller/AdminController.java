package com.dev.JournalApp.controller;

import com.dev.JournalApp.entity.User;
import com.dev.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAllEntries();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin-user")
    public ResponseEntity<?> createAdminUser(@RequestBody User user) {
        User user1 = userService.saveAdminUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
}
