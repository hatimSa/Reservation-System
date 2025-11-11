package com.WS_Project.modules.reservation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import com.WS_Project.modules.reservation.dto.request.ReservationRequestDTO;
import com.WS_Project.modules.reservation.dto.response.ReservationResponseDTO;
import com.WS_Project.modules.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservations", description = "Reservation management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "Create a new reservation", description = "Create a new restaurant reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reservation created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required")
    })
   @PostMapping
@PreAuthorize("isAuthenticated()")  // ou hasRole('USER')
public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody ReservationRequestDTO dto) {
    ReservationResponseDTO response = reservationService.createReservation(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}


    @Operation(summary = "Get reservation by ID", description = "Retrieve a specific reservation by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.getReservationById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all reservations", description = "Retrieve all reservations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required")
    })
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        List<ReservationResponseDTO> response = reservationService.getAllReservations();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update reservation", description = "Update an existing reservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationRequestDTO dto) {
        ReservationResponseDTO response = reservationService.updateReservation(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete reservation", description = "Delete a reservation by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reservation deleted successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
