package ecom.dto;

	public record ReviewResponse(Long id, Long productId, int rating, String comment, String username) {
	
	}