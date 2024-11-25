package com.example.project2.service;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import com.example.project2.repository.ReserRepo;
import com.example.project2.repository.ShopRepo;
import com.example.project2.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final UserRepo userRepo;
    private final ShopRepo shopRepo;
    private final ReserRepo reserRepo;
    private final HttpSession session;

    public boolean login(UserDto dto) {
        Optional<UserDto> result = userRepo.findByIdAndPassword(dto.getId(), dto.getPassword());
        boolean loginCheck;

        if (result.isEmpty()) {
            loginCheck = false;
        }
        else {
            session.setAttribute("user", result.get());
            loginCheck = true;
        }

        return loginCheck;
    }

    public String register(UserDto dto, String passwordCheck) {
        if (userRepo.existsById(dto.getId())) {
            return "1";
        }
        if (!dto.getPassword().equals(passwordCheck)) {
            return "2";
        }
        dto.setRole(3);
        userRepo.save(dto);
        return "3";
    }

    public List<ShopDto> shopList() {
        return shopRepo.findAllByOrderByNoDesc();
    }
    public List<ShopDto> ReserManage(UserDto user) {
        return shopRepo.findByUserId(user);
    }
    public List<ReserDto> reserManageListByShopNo(Integer shopNo) {
        ShopDto shop = shopRepo.findById(shopNo).orElseThrow(() -> new RuntimeException("Shop not found"));
        return reserRepo.findByShopNo(shop);
    }

    public void reserSave(ReserDto dto) {
        dto.setStatus(1);
        reserRepo.save(dto);
    }

    @Transactional
    public void reserDelete(ShopDto shopno) {
        reserRepo.deleteByShopNo(shopno);
    }
    public boolean isReserved(ShopDto shopNo, UserDto userId) {
        Optional<ReserDto> reservation = reserRepo.findByShopNoAndUserId(shopNo, userId);
        return reservation.isPresent(); // 존재 true, 없으면 false
    }

    public List<ReserDto> reserList(UserDto user) {
        return reserRepo.findByUserIdOrderByNoDesc(user);
    }

    public List<ReserDto> reserAdminList() {
        return reserRepo.findAllByOrderByResertimeDesc();
    }

    public ShopDto detailList(Integer no) {
        ShopDto shop = shopRepo.findById(no).orElse(null);
        return shop;
    }

    public void writer(ShopDto dto) {
        shopRepo.save(dto);
    }

    @Transactional
    public void delete(Integer no) {
        ShopDto shop = shopRepo.findById(no).orElseThrow(() -> new RuntimeException("Shop not found"));
        reserRepo.deleteByShopNo(shop);
        shopRepo.delete(shop);
    }

}
