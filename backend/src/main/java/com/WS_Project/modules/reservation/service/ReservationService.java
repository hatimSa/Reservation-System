package com.WS_Project.modules.reservation.service;

import com.WS_Project.modules.guest.entity.Guest;
import com.WS_Project.modules.guest.repository.GuestRepository;
import com.WS_Project.modules.reservation.dto.request.ReservationRequestDTO;
import com.WS_Project.modules.reservation.dto.response.ReservationResponseDTO;
import com.WS_Project.modules.reservation.entity.Reservation;
import com.WS_Project.modules.reservation.mapper.ReservationMapper;
import com.WS_Project.modules.reservation.repository.ReservationRepository;
import com.WS_Project.modules.restaurantTable.entity.RestaurantTable;
import com.WS_Project.modules.restaurantTable.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {
        // Fetch the guest and table entities
        Guest guest = guestRepository.findById(dto.getGuestId())
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + dto.getGuestId()));
        
        RestaurantTable table = restaurantTableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new RuntimeException("Restaurant table not found with id: " + dto.getTableId()));
        
        // Create reservation with proper entity relationships
        Reservation reservation = Reservation.builder()
                .date(dto.getDate())
                .time(dto.getTime())
                .numberOfGuests(dto.getNumberOfGuests())
                .tableType(dto.getTableType())
                .status(dto.getStatus() != null ? dto.getStatus() : Reservation.ReservationStatus.aktiv) // Use provided status or default to ACTIVE
                .guest(guest)
                .table(table)
                .build();
        
        return ReservationMapper.toResponse(reservationRepository.save(reservation));
    }

    public ReservationResponseDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return ReservationMapper.toResponse(reservation);
    }

    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO dto) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        
        // Fetch the guest and table entities if they are being updated
        if (dto.getGuestId() != 0) {
            Guest guest = guestRepository.findById(dto.getGuestId())
                    .orElseThrow(() -> new RuntimeException("Guest not found with id: " + dto.getGuestId()));
            reservation.setGuest(guest);
        }
        
        if (dto.getTableId() != 0) {
            RestaurantTable table = restaurantTableRepository.findById(dto.getTableId())
                    .orElseThrow(() -> new RuntimeException("Restaurant table not found with id: " + dto.getTableId()));
            reservation.setTable(table);
        }
        
        reservation.setDate(dto.getDate());
        reservation.setTime(dto.getTime());
        reservation.setNumberOfGuests(dto.getNumberOfGuests());
        reservation.setTableType(dto.getTableType());
        
        // Update status if provided
        if (dto.getStatus() != null) {
            reservation.setStatus(dto.getStatus());
        }
        
        return ReservationMapper.toResponse(reservationRepository.save(reservation));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
