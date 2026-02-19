package ecom.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price,
        String category,
        int stock
) {}


