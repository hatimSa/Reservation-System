package com.WS_Project.modules.restaurantTable.dto.response;

import com.WS_Project.modules.restaurantTable.entity.RestaurantTable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTableResponseDTO {
    private long id;
    private int tableNumber;
    private int capacity;
    private RestaurantTable.TableLocation location;
    private RestaurantTable.TableStatus status;
    private long restaurantId;
}
