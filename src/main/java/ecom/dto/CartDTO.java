package ecom.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(Long id, Long userId, List<CartItemDTO> items, BigDecimal total) {}