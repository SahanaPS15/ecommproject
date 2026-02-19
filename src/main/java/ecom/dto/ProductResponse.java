package ecom.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String category,  
        String name,
        BigDecimal price,
        int stock
) {}
