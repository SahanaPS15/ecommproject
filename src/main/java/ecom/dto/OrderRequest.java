package ecom.dto;

import java.util.List;

public record OrderRequest(List<OrderItemDTO> items, String couponCode) {}
