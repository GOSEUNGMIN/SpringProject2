package com.example.project2.controller;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import com.example.project2.repository.ReserRepo;
import com.example.project2.service.ShopService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final HttpSession session;
    private final ShopService shopService;
    private final ReserRepo reserRepo;
    @GetMapping
    public String shop(Model model) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<ShopDto> dto = shopService.shopList();
        model.addAttribute("shoplist", dto);
        return "shop/shop";
    }

    @GetMapping("/writer")
    public String writer() {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || user.getRole() == 3) {
            return "redirect:/shop";
        }
        return "shop/writer";
    }

    @PostMapping("/writer")
    public String writer(ShopDto dto) {
        shopService.writer(dto);
        return "redirect:/shop";
    }

    @GetMapping("/detail/{no}")
    public String detail(@PathVariable("no") Integer no, @Validated ShopDto dto, Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            return "redirect:/shop";
        }
        ShopDto shop = shopService.detailList(no);
        boolean isReserved = shopService.isReserved(dto, user);  // 예약이 되어 있으면 true, 아니면 false
        boolean isCancelled = false;
        if (isReserved) {
            // 예약이 되어 있으면, 해당 예약 상태를 확인하여 취소되었는지 확인
            Optional<ReserDto> reservation = reserRepo.findByShopNoAndUserIdAndStatusNot(shop, user, 3);
            if (reservation.isPresent() && reservation.get().getStatus() == 3) {  // 상태가 '취소'이면
                isCancelled = true;
                isReserved = false;
            }
        }
        model.addAttribute("isReserved", isReserved);
        model.addAttribute("isCancelled", isCancelled);
        model.addAttribute("detaillist", shop);
        return "shop/detail";
    }
    @GetMapping("/reser/{no}")
    public String reser(@PathVariable("no") Integer no, Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            return "redirect:/shop";
        }
        ShopDto shop = shopService.detailList(no);
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        model.addAttribute("nowtime", formattedTime);
        model.addAttribute("detaillist", shop);
        return "shop/reserdetail";
    }
    @PostMapping("/cancel/{no}")
    public String cancel(@PathVariable ShopDto no) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        shopService.reserDelete(no, user);
        return "redirect:/mypage";
    }

    @GetMapping("/modify/{no}")
    public String modify(@PathVariable("no") Integer no, Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || user.getRole() == 3) {
            return "redirect:/login";
        }
        ShopDto shop = shopService.detailList(no);
        model.addAttribute("modify", shop);

        return "shop/modify";
    }

    @PostMapping("/modify/{no}")
    public String modify(ShopDto dto) {
        shopService.writer(dto);

        return "redirect:/shop/detail/{no}";
    }
    @PostMapping("/delete/{no}")
    public String delete(@PathVariable("no") Integer no) {
        shopService.delete(no);
        return "redirect:/shop";
    }

    @PostMapping("/reserve/{no}")
    public String reserve(ReserDto dto) throws MessagingException {
        shopService.reserSave(dto);
        return "redirect:/mypage";
    }
}