package com.example.project2.service;

import com.example.project2.dto.ApiUserDto;
import com.example.project2.repository.ApiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    private ApiRepo apiRepo;

    public List<ApiUserDto> getAllUsers() {
        return apiRepo.findAll();
    }


    public Optional<ApiUserDto> findUserByID(String id) {
        return apiRepo.findById(id);
    }


    public ApiUserDto saveUser(ApiUserDto apiUserDto) {return apiRepo.save(apiUserDto);}


    public ApiUserDto updateUser(String id, ApiUserDto apiUserDto) {
        Optional<ApiUserDto> User = apiRepo.findById(id);
        if (User.isPresent()) {
            ApiUserDto user = User.get();

            user.setName(apiUserDto.getName());
            user.setEmail(apiUserDto.getEmail());

            return apiRepo.save(user);
        } else {
            return null;
        }
    }

    public boolean UserDelete(String id) {
        Optional<ApiUserDto> userDto = apiRepo.findById(id);
        if (userDto.isPresent()) {
            apiRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
