package com.WS_Project.modules.guest.repository;

import com.WS_Project.modules.guest.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    boolean existsByEmail(String email);
}