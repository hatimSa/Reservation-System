package com.apexiom.modules.guest.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestResponseDTO {
    private long id;
    private String name;
    private String phone;
    private String email;
}
