package com.apexiom.modules.guest.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestRequestDTO {
    private String name;
    private String phone;
    private String email;
}
