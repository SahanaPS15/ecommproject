package ecom.dto;

import java.math.BigDecimal;

public record SalesReportResponse(
        long totalOrders,
        BigDecimal totalRevenue,
        BigDecimal totalDiscount
) {}
