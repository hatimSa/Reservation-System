package com.WS_Project.modules.reservation.dto.response;

import com.WS_Project.modules.reservation.entity.Reservation;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDTO {
    private long id;
    private LocalDate date;
    private LocalTime time;
    private int numberOfGuests;
    private String tableType;
    private Reservation.ReservationStatus status;
    private long tableId;
    private long guestId;
    private String guestName;
    private String guestPhone;
}
