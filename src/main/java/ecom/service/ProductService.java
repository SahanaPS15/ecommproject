package ecom.service;

import ecom.dto.ProductRequest;
import ecom.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> filterProducts(String category, java.math.BigDecimal maxPrice);
}

