package com.WS_Project.modules.restaurant.repository;

import com.WS_Project.modules.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    boolean existsByName(String name);
}
