package com.doddanna.demo.services;

import com.doddanna.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private ConcurrentHashMap<String,User> inMemoryMap=new ConcurrentHashMap<>();
    @Override
    public Optional<User> save(User user) {
        if(inMemoryMap.containsKey(user.getEmail().toLowerCase()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User email is already registered");
        user.setId(UUID.randomUUID().toString());
        inMemoryMap.put(user.getEmail().toLowerCase(),user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(new ArrayList<>(inMemoryMap.values()));
    }

    @Override
    public Optional<List<User>> saveAll(List<User> userList) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return inMemoryMap.values().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUserId(String id) {
        Optional<User> userById = inMemoryMap.values().stream().filter(user -> user.getId().equals(id)).findFirst();
        if(!userById.isPresent())
            return Optional.empty();
        inMemoryMap.remove(userById.get().getEmail().toLowerCase());
        return Optional.of(userById.get());
    }

    @Override
    public Optional<User> deleteUserEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUser(User user) {
        return Optional.empty();
    }
}
