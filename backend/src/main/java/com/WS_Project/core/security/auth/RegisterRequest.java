package com.WS_Project.core.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;       
    private String email;      
    private String phone;      
    private String password;  
    private String address;   
    private String website;   
    private String logo;      
}
