package com.WS_Project.modules.reservation.dto.request;

import com.WS_Project.modules.reservation.entity.Reservation;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequestDTO {
    private LocalDate date;
    private LocalTime time;
    private int numberOfGuests;
    private String tableType;
    private long tableId;
    private long guestId;
    private Reservation.ReservationStatus status;
}
