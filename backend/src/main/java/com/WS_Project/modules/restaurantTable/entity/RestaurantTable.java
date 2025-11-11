package com.WS_Project.modules.restaurantTable.entity;

import com.WS_Project.modules.restaurant.entity.Restaurant;
import com.WS_Project.modules.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurant_tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int tableNumber;
    private int capacity;
    
    @Enumerated(EnumType.STRING)
    private TableLocation location;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public enum TableLocation {
        drinnen,
        drüben
    }

    public enum TableStatus {
        verfügbar,
        reserviert
    }
}
