package com.example.project2.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "shop")
public class ShopDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    private String name;
    private String detail;
    private int postcode;
    private String address;
    private String detailAddress;
    private LocalDateTime time;
    public String getFormattedtime() {
        return time.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH : mm"));
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDto userId;

}