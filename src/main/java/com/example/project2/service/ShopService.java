package com.example.project2.service;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import com.example.project2.repository.ReserRepo;
import com.example.project2.repository.ShopRepo;
import com.example.project2.repository.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final UserRepo userRepo;
    private final ShopRepo shopRepo;
    private final ReserRepo reserRepo;
    private final HttpSession session;
    private final MailService mailService;
    @PersistenceContext
    private EntityManager entityManager;

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
        return reserRepo.findByShopNoOrderByNoDesc(shop);
    }

    public void reserSave(ReserDto dto) throws MessagingException {
        dto.setStatus(1);
        reserRepo.save(dto);

        ShopDto shop = shopRepo.findById(dto.getShopNo().getNo()).orElseThrow(() -> new RuntimeException("Shop not"));
        UserDto user = (UserDto) session.getAttribute("user");

        String mail = shop.getUserId().getEmail();

        mailService.sendReserMail(mail, user.getId(), shop.getName(),
                dto.getResertime().toString(), dto.getReserdetail());
    }

    @Transactional
    public void reserDelete(ShopDto shopNo, UserDto userId) {
        Optional<ReserDto> reservation = reserRepo.findByShopNoAndUserIdAndStatusNot(shopNo, userId, 3);
        if (reservation.isPresent()) {
            ReserDto reserDto = reservation.get();
            reserDto.setStatus(3);
            reserRepo.save(reserDto);
        }
    }
    public boolean isReserved(ShopDto shopNo, UserDto userId) {
        Optional<ReserDto> reservation = reserRepo.findByShopNoAndUserIdAndStatusNot(shopNo, userId, 3);
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

    public void updateStatus(UserDto userId, ShopDto shopNo, int status, Integer no) {
        ReserDto dto = (ReserDto) reserRepo.findByUserIdAndShopNoAndNo(userId, shopNo, no);

        if (dto != null) {
            dto.setStatus(status);
            reserRepo.save(dto);
        }
    }
}
