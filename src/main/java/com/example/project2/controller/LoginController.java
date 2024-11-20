package com.example.project2.controller;

import com.example.project2.dto.UserDto;
import com.example.project2.service.ShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController
{
    private final ShopService shopService;
    private final HttpSession session;

    @GetMapping("/login")
    public String login()
    {
        return "login/login";
    }

    @PostMapping("login")
    public String login(@Validated UserDto dto, Model model)
    {
        Boolean check = shopService.login(dto);
        if (check == true)
        {
            return "redirect:/";
        }
        else
        {
            return "login/login";
        }
    }

    @PostMapping("logout")
    public String logout()
    {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register()
    {
        return "login/register";
    }


}
