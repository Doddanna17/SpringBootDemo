package com.doddanna.demo.services;

import com.doddanna.demo.dtos.UserDto;
import com.doddanna.demo.exceptions.BadRequestException;
import com.doddanna.demo.exceptions.UserNotFoundException;
import com.doddanna.demo.models.User;
import com.doddanna.demo.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("UserServiceDBImpl")
public class UserServiceDBImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> save(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setId(UUID.randomUUID().toString());
        Optional<UserDto> byEmailId = userRepository.findByEmailId(user.getEmail());
        if(byEmailId.isPresent())
            throw new BadRequestException("User email is already registered");
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userRepository.save(userDto);
        return Optional.of(user);
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getAll() {
        List<UserDto> all = userRepository.findAll();
        List<User> collect = all.stream().map(entry -> {
            User user = new User();
            BeanUtils.copyProperties(entry, user);
            return user;
        }).collect(Collectors.toList());
        return Optional.of(collect);
    }

    @Override
    public Optional<List<User>> saveAll(List<User> userList) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(String id) {
        Optional<UserDto> byId = userRepository.findById(id);
        if(byId.isEmpty())
            throw new UserNotFoundException("User nof found for "+id);
        User u=new User();
        BeanUtils.copyProperties(byId.get(),u);
        return Optional.of(u);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<UserDto> byEmailId = userRepository.findByEmailId(email.toLowerCase());
        if(byEmailId.isPresent())
            throw new BadRequestException("User email is already registered");
        User user=new User();
        BeanUtils.copyProperties(byEmailId.get(),user);
        return Optional.of(user);
    }

    @Override
    public Optional<Boolean> deleteUserId(String id) {
        Optional<UserDto> byId = userRepository.findById(id);
        if(byId.isEmpty())
            throw new UserNotFoundException("User nof found for "+id);
        userRepository.deleteById(id);
        return Optional.of(true);
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
