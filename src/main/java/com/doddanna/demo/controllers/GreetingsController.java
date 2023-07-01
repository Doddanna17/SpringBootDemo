package com.doddanna.demo.controllers;

import com.doddanna.demo.models.Greetings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    //Path variables needs to be used when it is required as mandatory input of resource
    @GetMapping("/{message}/{to}")
    public ResponseEntity<Greetings> greeting(@PathVariable String message, @PathVariable("to") String nameOfSender){
        return ResponseEntity.ok(Greetings
                .builder()
                        .message(message)
                        .to(nameOfSender)
                        .greetedOn(new Date())
                .build());
    }
}
