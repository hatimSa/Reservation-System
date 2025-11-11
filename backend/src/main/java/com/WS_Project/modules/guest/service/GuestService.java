package com.WS_Project.modules.guest.service;

import com.WS_Project.modules.guest.dto.request.GuestRequestDTO;
import com.WS_Project.modules.guest.dto.response.GuestResponseDTO;
import com.WS_Project.modules.guest.entity.Guest;
import com.WS_Project.modules.guest.mapper.GuestMapper;
import com.WS_Project.modules.guest.repository.GuestRepository;
import com.WS_Project.modules.reservation.dto.response.ReservationResponseDTO;
import com.WS_Project.modules.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestResponseDTO createGuest(GuestRequestDTO dto) {
        Guest guest = GuestMapper.toEntity(dto);
        return GuestMapper.toResponse(guestRepository.save(guest));
    }

    public GuestResponseDTO getGuestById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
        return GuestMapper.toResponse(guest);
    }

    public List<GuestResponseDTO> getAllGuests() {
        return guestRepository.findAll().stream()
                .map(GuestMapper::toResponse)
                .collect(Collectors.toList());
    }

    public GuestResponseDTO updateGuest(Long id, GuestRequestDTO dto) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
        guest.setName(dto.getName());
        guest.setPhone(dto.getPhone());
        guest.setEmail(dto.getEmail());
        return GuestMapper.toResponse(guestRepository.save(guest));
    }

    public List<ReservationResponseDTO> getGuestReservations(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
        return guest.getReservations().stream()
                .map(ReservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}
