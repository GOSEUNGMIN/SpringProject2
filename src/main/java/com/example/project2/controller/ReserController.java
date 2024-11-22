package com.example.project2.controller;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import com.example.project2.service.ShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reser")
public class ReserController {
    private final HttpSession session;
    private final ShopService shopService;
    @GetMapping
    public String reser(Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || user.getRole() == 3) {
            return "redirect:/login";
        }
        List<ShopDto> dto = shopService.ReserManage(user);
        model.addAttribute("manage", dto);
        return "reser/reser";
    }

    @GetMapping("/manager/{no}")
    public String manager(@PathVariable("no") Integer no, Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || user.getRole() == 3) {
            return "redirect:/login";
        }
        List<ReserDto> manager = shopService.reserManageListByShopNo(no);
        model.addAttribute("managedetail",manager);
        return "reser/manager";
    }
}
