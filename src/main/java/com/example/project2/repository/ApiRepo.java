package com.example.project2.repository;

import com.example.project2.dto.ApiUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepo extends JpaRepository<ApiUserDto, String> {

}