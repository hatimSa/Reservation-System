package com.WS_Project.modules.restaurantTable.mapper;

import com.WS_Project.modules.restaurantTable.dto.request.RestaurantTableRequestDTO;
import com.WS_Project.modules.restaurantTable.dto.response.RestaurantTableResponseDTO;
import com.WS_Project.modules.restaurantTable.entity.RestaurantTable;
import com.WS_Project.modules.restaurant.entity.Restaurant;

public class RestaurantTableMapper {
    public static RestaurantTable toEntity(RestaurantTableRequestDTO dto, Restaurant restaurant) {
        if (dto == null)
            return null;
        return RestaurantTable.builder()
                .tableNumber(dto.getTableNumber())
                .capacity(dto.getCapacity())
                .location(dto.getLocation())
                .status(dto.getStatus())
                .restaurant(restaurant)
                .build();
    }

    public static RestaurantTableResponseDTO toResponse(RestaurantTable table) {
        if (table == null)
            return null;
        return RestaurantTableResponseDTO.builder()
                .id(table.getId())
                .tableNumber(table.getTableNumber())
                .capacity(table.getCapacity())
                .location(table.getLocation())
                .status(table.getStatus())
                .restaurantId(table.getRestaurant().getId())
                .build();
    }
}
