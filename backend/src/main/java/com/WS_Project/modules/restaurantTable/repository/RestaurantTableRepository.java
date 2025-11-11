package com.WS_Project.modules.restaurantTable.repository;

import com.WS_Project.modules.restaurantTable.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    boolean existsByTableNumber(int tableNumber);
}
