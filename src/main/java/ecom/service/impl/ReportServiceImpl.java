package ecom.service.impl;

import ecom.entity.Order;
import ecom.entity.OrderItem;
import ecom.repository.OrderRepository;
import ecom.service.ReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final OrderRepository orderRepository;

    public ReportServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Map<String, Object> generateSalesReport() {
        List<Order> orders = orderRepository.findAll();

        BigDecimal totalRevenue = orders.stream()
                .map(Order::getFinalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long totalOrders = orders.size();

        Map<String, Object> report = new HashMap<>();
        report.put("totalRevenue", totalRevenue);
        report.put("totalOrders", totalOrders);
        return report;
    }

    @Override
    public Map<String, Object> getTopSellingProducts() {
        List<Order> orders = orderRepository.findAll();

        Map<String, Long> productSales = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getName(),
                        Collectors.summingLong(OrderItem::getQuantity)
                ));

        // Sort by sales (descending)
        LinkedHashMap<String, Long> sorted = productSales.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        Map<String, Object> report = new HashMap<>();
        report.put("topProducts", sorted);
        return report;
    }

	@Override
	public BigDecimal getTotalSales() {
		// TODO Auto-generated method stub
		return null;
	}
}
