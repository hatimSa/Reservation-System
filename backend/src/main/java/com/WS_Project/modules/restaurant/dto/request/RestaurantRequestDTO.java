package com.WS_Project.modules.restaurant.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequestDTO {
    private String name;
    private String address;
    private String type;
    private Long ownerId;
}
