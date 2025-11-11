package com.WS_Project.modules.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequestDTO {
    private String currentPassword;
    private String newPassword;
}
