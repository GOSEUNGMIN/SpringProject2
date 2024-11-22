package com.example.project2.controller;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.UserDto;
import com.example.project2.service.ShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final HttpSession session;
    private final ShopService shopService;
    @GetMapping
    public String mypage(Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<ReserDto> reser = shopService.reserList(user);
        model.addAttribute("reserlist", reser);

        return "mypage/mypage";
    }
}
