package com.example.project2.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reser")
public class ReserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDto userId;

    private LocalDateTime resertime;
    private String reserdetail;

    @ManyToOne
    @JoinColumn(name = "shop_no")
    private ShopDto shopNo;
}
