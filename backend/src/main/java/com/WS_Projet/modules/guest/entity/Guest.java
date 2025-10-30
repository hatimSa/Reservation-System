package com.apexiom.modules.guest.entity;

import com.apexiom.modules.reservation.entity.Reservation;
import com.apexiom.modules.restaurant.entity.Restaurant;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    
    // Social login support
    private String provider; // e.g., "GOOGLE"
    
    @Column(name = "provider_user_id")
    private String providerUserId; // Google 'sub'
    
    private Boolean emailVerified;
    
    private String pictureUrl;
    
    private java.time.OffsetDateTime lastLoginAt;
    
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Reservation> reservations;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;



    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();
}
