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
public class ReserDto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private LocalDateTime time;
    private String userId;
    private int shopNo;
}
