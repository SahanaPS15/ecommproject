package ecom.service.impl;

import ecom.dto.ReviewRequest;
import ecom.dto.ReviewResponse;
import ecom.entity.Product;
import ecom.entity.Review;
import ecom.entity.User;
import ecom.exception.ResourceNotFoundException;
import ecom.repository.ProductRepository;
import ecom.repository.ReviewRepository;
import ecom.repository.UserRepository;
import ecom.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ProductRepository productRepository,
                             UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewResponse addReview(Long userId, Long productId, ReviewRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Review review = new Review(user, product, request.rating(), request.comment());
        reviewRepository.save(review);

        return new ReviewResponse(review.getId(), productId, review.getRating(), review.getComment(), user.getUsername());
    }

    @Override
    public List<ReviewResponse> getProductReviews(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return reviewRepository.findByProduct(product).stream()
                .map(r -> new ReviewResponse(r.getId(), productId, r.getRating(), r.getComment(), r.getUser().getUsername()))
                .collect(Collectors.toList());
    }
    
    //getTopSellingProducts
	public List<Product> getTopReviewedProducts() {
		return reviewRepository.findTopByOrderByRatingDesc().stream().map(Review::getProduct).distinct()
				.collect(Collectors.toList());
	}
    
}

