package com.example.project2.controller;

import com.example.project2.dto.ReserDto;
import com.example.project2.dto.ShopDto;
import com.example.project2.dto.UserDto;
import com.example.project2.repository.ReserRepo;
import com.example.project2.service.ShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reser")
public class ReserController {
    private final HttpSession session;
    private final ShopService shopService;
    private final ReserRepo reserRepo;
    @GetMapping
    public String reser(Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || user.getRole() == 3) {
            return "redirect:/login";
        }
        List<ShopDto> dto = shopService.ReserManage(user);
        model.addAttribute("manager", dto);
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
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("userId") UserDto userId, @RequestParam("status")int status,
                               @RequestParam("shopNo") ShopDto shopNo, @RequestParam("no") Integer no) {
        // 예약 상태 업데이트 처리
        shopService.updateStatus(userId, shopNo, status, no);

        // 처리 완료 후 목록으로 리다이렉트
        return "redirect:/reser";
    }
}
