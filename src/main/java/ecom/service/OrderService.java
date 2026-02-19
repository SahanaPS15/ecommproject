package ecom.service;

import java.util.List;

import ecom.dto.OrderRequest;
import ecom.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(Long userId, OrderRequest request);
	Object getOrdersByUser(Long userId);
	List<OrderResponse> getOrderHistoryByUser(Long userId);
}

