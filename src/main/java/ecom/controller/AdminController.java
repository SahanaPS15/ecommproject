package ecom.controller;

import ecom.mapper.OrderMapper;
import ecom.dto.OrderResponse;
import ecom.dto.ProductResponse;
import ecom.dto.UserResponse;
import ecom.service.AdminService;
import ecom.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/reports")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final ReportService reportService;

    public AdminController(AdminService adminService, ReportService reportService) {
        this.adminService = adminService;
        this.reportService = reportService;
    }

    // Manage Products
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(adminService.getAllProducts()
            .stream()
            .map(p -> new ProductResponse(
                    p.getId(),
                    p.getCategory(),
                    p.getName(),
                    p.getPrice(),
                    p.getStock()
            ))
            .toList());
    }

    // Manage Users
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers()
            .stream()
            .map(u -> new UserResponse(
                    u.getId(),
                    u.getUsername(),
                    u.getRoles()
            ))
            .toList());
    }

    // Manage Orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(adminService.getAllOrders()
            .stream()
            .map(OrderMapper::toResponse) // map to safe DTO
            .toList());
    }

    // Sales Report
    @GetMapping("/sales-report")
    public ResponseEntity<Map<String, Object>> getSalesReport() {
        return ResponseEntity.ok(reportService.generateSalesReport());
    }

    // Top Selling Products
    @GetMapping("/top-products-report")
    public ResponseEntity<Map<String, Object>> getTopProducts() {
        return ResponseEntity.ok(reportService.getTopSellingProducts());
    }
}
