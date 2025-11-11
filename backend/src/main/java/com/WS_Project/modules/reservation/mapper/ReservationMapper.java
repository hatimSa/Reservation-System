package com.WS_Project.modules.reservation.mapper;

import com.WS_Project.modules.reservation.dto.request.ReservationRequestDTO;
import com.WS_Project.modules.reservation.dto.response.ReservationResponseDTO;
import com.WS_Project.modules.reservation.entity.Reservation;

public class ReservationMapper {
    public static Reservation toEntity(ReservationRequestDTO dto) {
        if (dto == null)
            return null;
        return Reservation.builder()
                .date(dto.getDate())
                .time(dto.getTime())
                .numberOfGuests(dto.getNumberOfGuests())
                .tableType(dto.getTableType())
                .build();
    }

    public static ReservationResponseDTO toResponse(Reservation reservation) {
        if (reservation == null)
            return null;
        return ReservationResponseDTO.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .time(reservation.getTime())
                .numberOfGuests(reservation.getNumberOfGuests())
                .tableType(reservation.getTableType())
                .status(reservation.getStatus())
                .tableId(reservation.getTable().getId())
                .guestId(reservation.getGuest().getId())
                .guestName(reservation.getGuest() != null ? reservation.getGuest().getName() : null)
                .guestPhone(reservation.getGuest() != null ? reservation.getGuest().getPhone() : null)
                .build();
    }
}
