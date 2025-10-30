package com.apexiom.modules.feedback.repository;

import com.apexiom.modules.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByGuestId(Long guestId);
    List<Feedback> findByRestaurantId(Long restaurantId);
}
