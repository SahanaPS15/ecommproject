package ecom.controller;

import ecom.dto.ReviewRequest;
import ecom.dto.ReviewResponse;
import ecom.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Add a review for a product
    @PostMapping("/{userId}/product/{productId}")
    public ResponseEntity<ReviewResponse> addReview(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody ReviewRequest request
    ) {
    	 ReviewResponse response = reviewService.addReview(userId, productId, request);
    	    return ResponseEntity.ok(response);
    }

    // Get all reviews for a product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsForProduct(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(reviewService.getProductReviews(productId));
    }
}
