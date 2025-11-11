package com.WS_Project.modules.restaurantTable.dto.request;

import com.WS_Project.modules.restaurantTable.entity.RestaurantTable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTableRequestDTO {
    private int tableNumber;
    private int capacity;
    private RestaurantTable.TableLocation location;
    private RestaurantTable.TableStatus status;
    private long restaurantId;
}
