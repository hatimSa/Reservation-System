package com.apexiom.modules.feedback.controller;

import com.apexiom.modules.feedback.dto.request.FeedbackRequestDTO;
import com.apexiom.modules.feedback.dto.response.FeedbackResponseDTO;
import com.apexiom.modules.feedback.entity.Feedback;
import com.apexiom.modules.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

   

    // --- Liste des feedbacks par restaurant ---
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FeedbackResponseDTO>> getFeedbacksByRestaurant(
            @PathVariable Long restaurantId
    ) {
        List<FeedbackResponseDTO> feedbacks = feedbackService.getFeedbacksByRestaurant(restaurantId);
        return ResponseEntity.ok(feedbacks);
    }

    // --- Liste des feedbacks par guest ---
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<FeedbackResponseDTO>> getFeedbacksByGuest(
            @PathVariable Long guestId
    ) {
        List<FeedbackResponseDTO> feedbacks = feedbackService.getFeedbacksByGuest(guestId);
        return ResponseEntity.ok(feedbacks);
    }

    // --- Modifier un feedback ---
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> updateFeedback(
            @PathVariable Long id,
            @RequestBody FeedbackRequestDTO request
    ) {
        Feedback feedback = new Feedback();
        feedback.setMessage(request.getMessage());
        feedback.setRating(request.getRating());

        Feedback updated = feedbackService.updateFeedback(id, feedback);
        FeedbackResponseDTO response = com.apexiom.modules.feedback.mapper.FeedbackMapper.toResponse(updated);
        return ResponseEntity.ok(response);
    }

    // --- Supprimer un feedback ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
