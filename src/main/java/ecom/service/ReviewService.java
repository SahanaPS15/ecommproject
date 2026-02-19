package ecom.service;

import java.util.List;

import ecom.dto.ReviewRequest;
import ecom.dto.ReviewResponse;

public interface ReviewService {
    ReviewResponse addReview(Long userId, Long productId, ReviewRequest request);
    List<ReviewResponse> getProductReviews(Long productId);
}

