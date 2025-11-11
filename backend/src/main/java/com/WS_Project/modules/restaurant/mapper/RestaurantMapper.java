package com.WS_Project.modules.restaurant.mapper;

import com.WS_Project.modules.restaurant.dto.request.RestaurantRequestDTO;
import com.WS_Project.modules.restaurant.dto.response.RestaurantResponseDTO;
import com.WS_Project.modules.restaurant.entity.Restaurant;

public class RestaurantMapper {
    public static Restaurant toEntity(RestaurantRequestDTO dto) {
        if (dto == null)
            return null;
        return Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .type(dto.getType())
                .build();
    }

    public static RestaurantResponseDTO toResponse(Restaurant restaurant) {
        if (restaurant == null)
            return null;
        return RestaurantResponseDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .type(restaurant.getType())
                .ownerId(restaurant.getOwner().getId())
                .build();
    }
}
