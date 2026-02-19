package ecom.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(Long id, List<OrderItemDTO> items,BigDecimal subtotal, BigDecimal tax, BigDecimal discount, BigDecimal total, String status) {}