package com.WS_Project.modules.restaurant.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDTO {
    private long id;
    private String name;
    private String address;
    private String type;
    private Long ownerId;
}
