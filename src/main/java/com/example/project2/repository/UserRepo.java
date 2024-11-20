package com.example.project2.repository;

import com.example.project2.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserDto, String>
{
    Optional<UserDto> findByIdAndPassword(String id, String password);
}
