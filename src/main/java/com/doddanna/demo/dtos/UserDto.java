package com.doddanna.demo.dtos;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "user")
@Table(name = "users")
@Data
public class UserDto {
    @Id
    private String id;
    private String name;
    private String email;
}
