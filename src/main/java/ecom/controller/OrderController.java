package ecom.controller;

import ecom.dto.OrderRequest;
import ecom.dto.OrderResponse;
import ecom.service.OrderService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place an order
    @PostMapping("/{userId}/place")
    public ResponseEntity<OrderResponse> placeOrder(
            @PathVariable Long userId,
            @RequestBody(required = false) OrderRequest request
    ) {
        return ResponseEntity.ok(orderService.placeOrder(userId, request));
    }

    // Get order history
    @GetMapping("/user/{userId}/history")
    public List<OrderResponse> getHistoryByUser(@PathVariable Long userId) {
        return orderService.getOrderHistoryByUser(userId);
    }

}
