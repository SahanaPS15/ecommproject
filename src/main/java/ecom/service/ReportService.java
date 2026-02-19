package ecom.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ReportService {
    BigDecimal getTotalSales();
    Map<String, Object> getTopSellingProducts();
    Map<String, Object> generateSalesReport();
}

