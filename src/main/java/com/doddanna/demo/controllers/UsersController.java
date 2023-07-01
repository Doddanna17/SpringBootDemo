package com.doddanna.demo.controllers;

import com.doddanna.demo.models.User;
import com.doddanna.demo.services.UserService;
import com.doddanna.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    @Qualifier("UserServiceDBImpl")
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userValidator.validateUser(user);
        Optional<User> saveStatus = userService.save(user);
        return saveStatus.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        Optional<List<User>> all = userService.getAll();
        return ResponseEntity.ok(all.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        Optional<User> userById = userService.getUserById(id);
        return ResponseEntity.ok(userById.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        Optional<Boolean> userById = userService.deleteUserId(id);
        return ResponseEntity.ok(userById.get());
    }
}
