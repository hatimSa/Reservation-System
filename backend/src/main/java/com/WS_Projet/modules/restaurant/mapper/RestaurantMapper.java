package com.WS_Projet.modules.restaurant.mapper;

import com.WS_Projet.modules.restaurant.dto.request.CreateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.request.UpdateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.response.RestaurantResponse;
import com.WS_Projet.modules.restaurant.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantResponse toResponse(Restaurant r) {
        if (r == null) return null;
        return RestaurantResponse.builder()
                .id(r.getId())
                .name(r.getName())
                .address(r.getAddress())
                .phone(r.getPhone())
                .email(r.getEmail())
                .type(r.getType())
                .createdAt(r.getCreatedAt())
                .updatedAt(r.getUpdatedAt())
                .build();
    }

    public Restaurant fromCreateRequest(CreateRestaurantRequest req) {
        if (req == null) return null;
        Restaurant r = new Restaurant();
        r.setName(req.getName());
        r.setAddress(req.getAddress());
        r.setPhone(req.getPhone());
        r.setEmail(req.getEmail());
        r.setPassword(req.getPassword());
        r.setType(req.getType());
        return r;
    }

    public void updateEntityFromRequest(UpdateRestaurantRequest req, Restaurant entity) {
        if (req == null || entity == null) return;
        if (req.getName() != null) entity.setName(req.getName());
        if (req.getAddress() != null) entity.setAddress(req.getAddress());
        if (req.getPhone() != null) entity.setPhone(req.getPhone());
        if (req.getEmail() != null) entity.setEmail(req.getEmail());
        if (req.getPassword() != null) entity.setPassword(req.getPassword());
        if (req.getType() != null) entity.setType(req.getType());
    }
}
