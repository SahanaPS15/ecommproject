package ecom.mapper;

import ecom.dto.ReviewResponse;
import ecom.entity.Review;

public class ReviewMapper {
    public static ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
            review.getId(),
            review.getProduct().getId(),
            review.getRating(),
            review.getComment(),
            review.getUser().getUsername()
        );
    }
}

