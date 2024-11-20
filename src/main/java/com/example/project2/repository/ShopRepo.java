package com.example.project2.repository;

import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<ShopDto, Integer>
{

}
