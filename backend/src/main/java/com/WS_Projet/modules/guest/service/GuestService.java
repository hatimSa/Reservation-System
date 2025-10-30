package com.apexiom.modules.guest.service;

import com.apexiom.modules.guest.dto.request.GuestRequestDTO;
import com.apexiom.modules.guest.dto.response.GuestResponseDTO;
import com.apexiom.modules.guest.entity.Guest;
import com.apexiom.modules.guest.mapper.GuestMapper;
import com.apexiom.modules.guest.repository.GuestRepository;
import com.apexiom.modules.reservation.dto.response.ReservationResponseDTO;
import com.apexiom.modules.reservation.mapper.ReservationMapper;
import com.apexiom.modules.user.entity.User;
import com.apexiom.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final UserRepository userRepository;

    // Obtenir le user actuellement connecté
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Guest upsertFromGoogle(String provider, String providerUserId, String email, String name, String pictureUrl, boolean emailVerified) {
        return guestRepository.findByProviderAndProviderUserId(provider, providerUserId)
                .or(() -> guestRepository.findByEmail(email))
                .map(existing -> {
                    existing.setName(name);
                    existing.setEmail(email);
                    existing.setPictureUrl(pictureUrl);
                    existing.setEmailVerified(emailVerified);
                    existing.setProvider(provider);
                    existing.setProviderUserId(providerUserId);
                    existing.setLastLoginAt(java.time.OffsetDateTime.now());
                    return guestRepository.save(existing);
                })
                .orElseGet(() -> {
                    Guest created = Guest.builder()
                            .name(name)
                            .email(email)
                            .pictureUrl(pictureUrl)
                            .emailVerified(emailVerified)
                            .provider(provider)
                            .providerUserId(providerUserId)
                            .lastLoginAt(java.time.OffsetDateTime.now())
                            .build();
                    return guestRepository.save(created);
                });
    }

    public GuestResponseDTO createGuest(GuestRequestDTO dto) {
        Guest guest = GuestMapper.toEntity(dto);

        // Lier le guest au restaurant du user connecté
        User currentUser = getCurrentUser();
        if (currentUser.getRestaurant() == null) {
            throw new RuntimeException("User does not have a restaurant");
        }
        guest.setRestaurant(currentUser.getRestaurant());

        return GuestMapper.toResponse(guestRepository.save(guest));
    }

    public GuestResponseDTO getGuestById(Long id) {
        User currentUser = getCurrentUser();
        if (currentUser.getRestaurant() == null) {
            throw new RuntimeException("User does not have a restaurant");
        }

        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        // Vérifier que le guest appartient au restaurant du user connecté
        if (guest.getRestaurant().getId() != currentUser.getRestaurant().getId()) {
            throw new RuntimeException("Guest does not belong to your restaurant");
        }

        return GuestMapper.toResponse(guest);
    }

    public List<GuestResponseDTO> getAllGuests() {
        User currentUser = getCurrentUser();
        if (currentUser.getRestaurant() == null) {
            throw new RuntimeException("User does not have a restaurant");
        }

        // Retourner uniquement les guests du restaurant du user connecté
        return guestRepository.findByRestaurantId(currentUser.getRestaurant().getId()).stream()
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
