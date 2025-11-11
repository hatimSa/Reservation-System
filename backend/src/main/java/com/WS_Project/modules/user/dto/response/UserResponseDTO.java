package com.WS_Project.modules.user.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private long id;
    private String name;
    private String email;
    private String phone;
}
