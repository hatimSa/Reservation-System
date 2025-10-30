package com.apexiom.modules.feedback.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackRequestDTO {
    private String message;
    private int rating;
    private Long restaurantId; // facultatif si le frontend envoie l'id du restaurant
}
