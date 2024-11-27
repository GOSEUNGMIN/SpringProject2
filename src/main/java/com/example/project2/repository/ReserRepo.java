package com.example.project2.repository;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReserRepo extends JpaRepository<ReserDto, Long> {
    List<ReserDto> findByUserIdOrderByNoDesc(UserDto user);
    @EntityGraph(attributePaths = {"shopNo", "userId"})
    List<ReserDto> findAllByOrderByResertimeDesc();
    ReserDto findByUserIdAndShopNoAndNo(UserDto userId, ShopDto shopNo, Integer no);
    List<ReserDto> findByShopNoOrderByNoDesc(ShopDto shopNo);
    void deleteByShopNo(ShopDto shopNo);
    Optional<ReserDto> findByShopNoAndUserIdAndStatusNot(ShopDto shopNo, UserDto userId, int status);
}
