package com.example.project2.controller;

import com.example.project2.dto.ApiUserDto;
import com.example.project2.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping
    public List<ApiUserDto> getAllUser() {
        return apiService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiUserDto> getUser(@PathVariable String id) {
        Optional<ApiUserDto> Dto = apiService.findUserByID(id);
        if (Dto.isPresent()) {
            return ResponseEntity.ok(Dto.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping
    public ResponseEntity<ApiUserDto> createUser(@RequestBody ApiUserDto apiUserDto) {
        ApiUserDto save = apiService.saveUser(apiUserDto);
        return ResponseEntity.status(201).body(save);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiUserDto> updateUser(@PathVariable String id, @RequestBody ApiUserDto apiUserDto) {
        ApiUserDto update = apiService.updateUser(id, apiUserDto);
        if (update != null) {
            return ResponseEntity.ok(update);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean isDeleted = apiService.UserDelete(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();  // 성공 시 204, 실패 시 404 반환
    }
}
