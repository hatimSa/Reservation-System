package com.apexiom.modules.guest.controller;

import com.apexiom.modules.guest.dto.request.GuestRequestDTO;
import com.apexiom.modules.guest.dto.response.GuestResponseDTO;
import com.apexiom.modules.guest.service.GuestService;
import com.apexiom.modules.reservation.dto.response.ReservationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestResponseDTO> createGuest(@RequestBody GuestRequestDTO dto) {
        GuestResponseDTO response = guestService.createGuest(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> getGuestById(@PathVariable Long id) {
        GuestResponseDTO response = guestService.getGuestById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getAllGuests() {
        List<GuestResponseDTO> response = guestService.getAllGuests();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getGuestReservations(@PathVariable Long id) {
        List<ReservationResponseDTO> response = guestService.getGuestReservations(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> updateGuest(@PathVariable Long id, @RequestBody GuestRequestDTO dto) {
        GuestResponseDTO response = guestService.updateGuest(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}
