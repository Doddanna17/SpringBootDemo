package com.doddanna.demo.controllers;

import com.doddanna.demo.models.Greetings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/v1")
    public ResponseEntity<Greetings> createGreetingUsingParametersV1(@RequestParam(name = "message",required = false) String message,
                                                                   @RequestParam(name = "to", required = true) String to){
        return ResponseEntity.ok(Greetings
                .builder()
                .message(message==null?"System generated message":message)
                .to(to)
                .greetedOn(new Date())
                .build());
    }

    @PostMapping("/v2")
    public ResponseEntity<Greetings> createGreetingUsingParametersV2(@RequestBody Greetings greetings){
        if(greetings.getGreetedOn()==null)
            greetings.setGreetedOn(new Date());
        return ResponseEntity.ok(greetings);
    }
}
