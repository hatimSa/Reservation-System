package com.apexiom.modules.feedback.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackResponseDTO {
    private Long id;
    private String message;
    private int rating;
    private String guestName;
    private Long restaurantId;
    private String restaurantName;
}
