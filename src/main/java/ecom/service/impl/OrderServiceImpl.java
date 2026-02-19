package ecom.service.impl;

import ecom.dto.OrderRequest;
import ecom.dto.OrderResponse;
import ecom.entity.*;
import ecom.exception.ResourceNotFoundException;
import ecom.mapper.OrderMapper;
import ecom.repository.*;
import ecom.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            ProductRepository productRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderResponse placeOrder(Long userId, OrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CONFIRMED);
        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.getStock() < item.getQuantity()) {
                throw new IllegalStateException("Not enough stock for product: " + product.getName());
            }
            // Decrease inventory
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
         // Create order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);  // ✅ explicitly set
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            order.getItems().add(orderItem);

            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(0.1)); // 10% tax
        BigDecimal discount = subtotal.compareTo(BigDecimal.valueOf(500)) > 0
                ? subtotal.multiply(BigDecimal.valueOf(0.05)) // 5% discount
                : BigDecimal.ZERO;
        BigDecimal finalAmount = subtotal.add(tax).subtract(discount);

        order.setSubtotal(subtotal);  
        order.setTax(tax);
        order.setDiscount(discount);
        order.setTotal(finalAmount);   
        order.setStatus(OrderStatus.CONFIRMED);


        orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return OrderMapper.toResponse(order);
    }

	@Override
	public Object getOrdersByUser(Long userId) {
		return orderRepository.findById(userId)
				.stream()
				.map(OrderMapper::toResponse)
				.toList();
	}
	@Override
	public List<OrderResponse> getOrderHistoryByUser(Long userId) {
	    List<Order> orders = orderRepository.findByUserId(userId);
	    return orders.stream()
	    		.map(OrderMapper::toResponse)
	                 .toList();
	}
	
	

}
