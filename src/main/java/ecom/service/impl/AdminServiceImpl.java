package ecom.service.impl;

import ecom.entity.Product;
import ecom.entity.User;
import ecom.entity.Order;
import ecom.repository.ProductRepository;
import ecom.repository.UserRepository;
import ecom.repository.OrderRepository;
import ecom.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AdminServiceImpl(ProductRepository productRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
