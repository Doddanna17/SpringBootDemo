package com.doddanna.demo.services;


import com.doddanna.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> save(User user);
    public Optional<User> update(User user);
    public Optional<List<User>> getAll();

    public Optional<List<User>> saveAll(List<User> userList);

    public Optional<User> getUserById(String id);
    public Optional<User> getUserByEmail(String email);

    public Optional<User> deleteUserId(String id);
    public Optional<User> deleteUserEmail(String email);
    public Optional<User> deleteUser(User user);
}
