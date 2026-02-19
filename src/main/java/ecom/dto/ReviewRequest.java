package ecom.dto;

public record ReviewRequest(Long productId, int rating, String comment) {}
