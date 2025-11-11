package com.WS_Project.modules.reservation.repository;

import com.WS_Project.modules.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByGuestEmail(String email);
}
