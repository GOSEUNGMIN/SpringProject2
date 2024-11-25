package com.example.project2.repository;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReserRepo extends JpaRepository<ReserDto, Long> {
    List<ReserDto> findByUserIdOrderByNoDesc(UserDto user);
    @EntityGraph(attributePaths = {"shopNo", "userId"})
    List<ReserDto> findAllByOrderByResertimeDesc();
    List<ReserDto> findByShopNo(ShopDto shopNo);
    void deleteByShopNo(ShopDto shopNo);
    Optional<ReserDto> findByShopNoAndUserId(ShopDto shopNo, UserDto userId);

}
