package ecom.mapper;

import ecom.dto.ProductRequest;
import ecom.dto.ProductResponse;
import ecom.entity.Product;

public class ProductMapper {

    // 🔹 Convert ProductRequest -> Product Entity
    public static Product toEntity(ProductRequest request) {
        if (request == null) return null;

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setCategory(request.category());
        product.setStock(request.stock());
        return product;
    }

    // 🔹 Convert Product Entity -> ProductResponse
    public static ProductResponse toResponse(Product entity) {
        if (entity == null) return null;

        return new ProductResponse(
                entity.getId(),
                entity.getCategory(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock()
        );
    }
}
