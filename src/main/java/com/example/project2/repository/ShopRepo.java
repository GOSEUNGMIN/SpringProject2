package com.example.project2.repository;

import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepo extends JpaRepository<ShopDto, Integer> {
    List<ShopDto> findAllByOrderByNoDesc();
    List<ShopDto> findByUserId(UserDto user);
}
