package com.WS_Projet.modules.restaurant.service;

import com.WS_Projet.modules.restaurant.dto.request.CreateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.request.UpdateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse create(CreateRestaurantRequest req);
    RestaurantResponse update(Long id, UpdateRestaurantRequest req);
    RestaurantResponse getById(Long id);
    List<RestaurantResponse> getAll();
    void delete(Long id);
}
