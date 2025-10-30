package com.apexiom.modules.guest.mapper;

import com.apexiom.modules.guest.dto.request.GuestRequestDTO;
import com.apexiom.modules.guest.dto.response.GuestResponseDTO;
import com.apexiom.modules.guest.entity.Guest;

public class GuestMapper {
    public static Guest toEntity(GuestRequestDTO dto) {
        if (dto == null)
            return null;
        return Guest.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

    public static GuestResponseDTO toResponse(Guest guest) {
        if (guest == null)
            return null;
        return GuestResponseDTO.builder()
                .id(guest.getId())
                .name(guest.getName())
                .phone(guest.getPhone())
                .email(guest.getEmail())
                .build();
    }
}
