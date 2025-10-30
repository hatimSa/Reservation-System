package com.apexiom.modules.feedback.service;

import com.apexiom.modules.feedback.dto.response.FeedbackResponseDTO;
import com.apexiom.modules.feedback.entity.Feedback;
import com.apexiom.modules.feedback.mapper.FeedbackMapper;
import com.apexiom.modules.feedback.repository.FeedbackRepository;
import com.apexiom.modules.guest.entity.Guest;
import com.apexiom.modules.guest.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final GuestRepository guestRepository;

    // --- Création ---
    public Feedback createFeedback(Long guestId, Feedback feedback) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
        feedback.setGuest(guest);
        return feedbackRepository.save(feedback);
    }

    // --- Affichage par guest (avec DTO) ---
    public List<FeedbackResponseDTO> getFeedbacksByGuest(Long guestId) {
        return feedbackRepository.findByGuestId(guestId)
                .stream()
                .map(FeedbackMapper::toResponse)
                .collect(Collectors.toList());
    }

    // --- Affichage par restaurant (avec DTO) ---
    public List<FeedbackResponseDTO> getFeedbacksByRestaurant(Long restaurantId) {
        return feedbackRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(FeedbackMapper::toResponse)
                .collect(Collectors.toList());
    }

    // --- Modification ---
    public Feedback updateFeedback(Long id, Feedback feedback) {
        Feedback existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        existing.setMessage(feedback.getMessage());
        existing.setRating(feedback.getRating());
        return feedbackRepository.save(existing);
    }

    // --- Suppression ---
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
