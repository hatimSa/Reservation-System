package com.apexiom.modules.feedback.mapper;

import com.apexiom.modules.feedback.dto.response.FeedbackResponseDTO;
import com.apexiom.modules.feedback.entity.Feedback;

public class FeedbackMapper {

    public static FeedbackResponseDTO toResponse(Feedback feedback) {
        if (feedback == null) return null;

        return new FeedbackResponseDTO(
            feedback.getId(),
            feedback.getMessage(),
            feedback.getRating(),
            feedback.getGuest() != null ? feedback.getGuest().getName() : null,
            feedback.getRestaurant() != null ? feedback.getRestaurant().getId() : null,
            feedback.getRestaurant() != null ? feedback.getRestaurant().getName() : null
        );
    }
}
