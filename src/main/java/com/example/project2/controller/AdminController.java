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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final HttpSession session;
    private final ShopService shopService;
    @GetMapping
    public String admin(Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || user.getRole() != 1) {
            return "redirect:/login";
        }
        List<ReserDto> admin = shopService.reserAdminList();
        model.addAttribute("adminlist", admin);

        return "admin/admin";
    }
}
