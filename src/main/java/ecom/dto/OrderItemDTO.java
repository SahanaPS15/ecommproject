package ecom.dto;

import java.math.BigDecimal;

public record OrderItemDTO(Long id, ProductResponse product, int quantity, BigDecimal price) {}