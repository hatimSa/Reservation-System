package com.WS_Project.modules.user.mapper;

import com.WS_Project.modules.user.dto.request.UserRequestDTO;
import com.WS_Project.modules.user.dto.response.UserResponseDTO;
import com.WS_Project.modules.user.entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO dto) {
        if (dto == null)
            return null;
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponseDTO toResponse(User user) {
        if (user == null)
            return null;
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
