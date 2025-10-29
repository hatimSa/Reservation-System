package com.WS_Projet.modules.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
