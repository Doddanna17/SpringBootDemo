package com.doddanna.demo.repositories;

import com.doddanna.demo.dtos.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto,String> {

    @Query("select u from user u where u.email= ?1")
    public Optional<UserDto> findByEmailId(String emailId);
}
