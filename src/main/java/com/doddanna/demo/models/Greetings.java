package com.doddanna.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Greetings {
    private String message;
    private String to;
    private Date greetedOn;
}
