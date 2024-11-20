package com.example.project2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reser")
public class ReserController
{
    @GetMapping
    public String reser()
    {
        return "reser/reser";
    }

    @GetMapping("/manager")
    public String manager()
    {
        return "reser/manager";
    }
}
