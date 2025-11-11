package com.WS_Project.modules.guest.entity;

import com.WS_Project.modules.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "guests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;
    
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
