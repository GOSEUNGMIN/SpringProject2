package com.example.project2.controller;

import com.example.project2.dto.UserDto;
import com.example.project2.service.BatchService;
import com.example.project2.service.ShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {
    private final ShopService shopService;
    private final BatchService batchService;
    private final HttpSession session;

    @GetMapping("/login")
    public String login() {
        Object user = session.getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }
        return "login/login";
    }

    @PostMapping("login")
    public String login(@Validated UserDto dto, Model model) {
        Boolean check = shopService.login(dto);
        if (check == true) {
            batchService.sendLogin();
            return "redirect:/";
        }
        else {
            model.addAttribute("msg", "아이디 혹은 패스워드를 확인해주세요.");
            return "login/login";
        }
    }

    @PostMapping("logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        Object user = session.getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }
        return "login/register";
    }

    @PostMapping("/register")
    public String register(UserDto dto, String passwordCheck, Model model, RedirectAttributes redirectAttributes) {
        String msg = shopService.register(dto, passwordCheck);
        if (msg.equals("1")) {
            model.addAttribute("msg", "중복된 아이디 입니다.");
            return "login/register";
        }
        else if (msg.equals("2")) {
            model.addAttribute("msg2", "비밀번호를 확인 해주세요.");
            return "login/register";
        }
        else {
            redirectAttributes.addFlashAttribute("msg3", "회원가입이 완료 되었습니다.");
            return "redirect:/login";
        }
    }
}
