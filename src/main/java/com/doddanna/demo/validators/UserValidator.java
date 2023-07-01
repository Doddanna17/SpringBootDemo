package com.doddanna.demo.validators;

import com.doddanna.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserValidator {
    public void validateUser(User user){
        if(user==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is invalid");
        if(user.getEmail()==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is invalid");
        if(user.getName()==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is invalid");
    }
}
