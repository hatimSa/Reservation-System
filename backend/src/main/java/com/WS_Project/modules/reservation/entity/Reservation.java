package com.WS_Project.modules.reservation.entity;

import com.WS_Project.modules.restaurantTable.entity.RestaurantTable;
import com.WS_Project.modules.guest.entity.Guest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private LocalTime time;
    private int numberOfGuests;
    private String tableType;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    public enum ReservationStatus {
        aktiv,
        Abgelaufen,
        abgesagt
    }
}
