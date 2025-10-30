package com.apexiom.modules.guest.repository;

import com.apexiom.modules.guest.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    boolean existsByEmail(String email);
    Optional<Guest> findByEmail(String email);
    Optional<Guest> findByProviderAndProviderUserId(String provider, String providerUserId);
    
    List<Guest> findByRestaurantId(Long restaurantId);

}