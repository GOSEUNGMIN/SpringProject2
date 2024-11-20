package com.example.project2.repository;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserRepo extends JpaRepository<ReserDto, Integer>
{

}
