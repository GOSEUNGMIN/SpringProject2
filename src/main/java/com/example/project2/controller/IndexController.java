package com.example.project2.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {
    private final HttpSession session;
    @GetMapping
    public String index(Model model) {
        Object user = session.getAttribute("user");

        if (user != null) {
            model.addAttribute("user", user);
        }
        else {
            model.addAttribute("user",null);
        }

        return "index";
    }
}
