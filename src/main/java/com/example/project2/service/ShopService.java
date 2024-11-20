package com.example.project2.service;

import com.example.project2.dto.UserDto;
import com.example.project2.repository.ReserRepo;
import com.example.project2.repository.ShopRepo;
import com.example.project2.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService
{
    private final UserRepo userRepo;
    private final ShopRepo shopRepo;
    private final ReserRepo reserRepo;
    private final HttpSession session;

    public boolean login(UserDto dto)
    {
        Optional<UserDto> result = userRepo.findByIdAndPassword(dto.getId(), dto.getPassword());
        boolean loginCheck;

        if (result.isEmpty())
        {
            loginCheck = false;
        }
        else
        {
            session.setAttribute("user", result.get());
            loginCheck = true;
        }
        return loginCheck;
    }
}
