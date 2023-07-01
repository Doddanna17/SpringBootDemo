package com.doddanna.demo.controllers;

import com.doddanna.demo.models.User;
import com.doddanna.demo.services.UserService;
import com.doddanna.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
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
        if(userById.isPresent())
            return ResponseEntity.ok(userById.get());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id){
        Optional<User> userById = userService.deleteUserId(id);
        if(userById.isPresent())
            return ResponseEntity.ok(userById.get());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }
}
