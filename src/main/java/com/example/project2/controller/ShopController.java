package com.example.project2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController
{
    @GetMapping
    public String shop()
    {
        return "shop/shop";
    }
    @GetMapping("/detail")
    public String detail()
    {
        return "shop/detail";
    }
}
