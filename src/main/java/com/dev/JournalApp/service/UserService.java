package com.dev.JournalApp.service;


import com.dev.JournalApp.entity.User;
import com.dev.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public ResponseEntity<?> saveNewUser(User user) {
        try {
            user.setRoles(Arrays.asList("USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return ResponseEntity.ok(userRepository.save(user));
        } catch (Exception e) {
            log.error("Error while saving user", e);
            return ResponseEntity.internalServerError().body("Error while saving user : " + e.getMessage());
        }
    }

    public User saveAdminUser(User user) {
        user.setRoles(Arrays.asList("USER","ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllEntries() {
        return userRepository.findAll();
    }

    public Optional<User> findByElementId(ObjectId id) {
        return userRepository.findById(id);
    }

    public boolean deleteById(ObjectId id) {
        userRepository.deleteById(id);
        return true;
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
        return true;
    }

}
