package ecom.service;

import ecom.entity.Product;
import ecom.entity.User;
import ecom.entity.Order;

import java.util.List;

public interface AdminService {
    List<Product> getAllProducts();
    List<User> getAllUsers();
    List<Order> getAllOrders();
}
