package ecom.service.impl;

import ecom.dto.ProductRequest;
import ecom.dto.ProductResponse;
import ecom.entity.Product;
import ecom.exception.ResourceNotFoundException;
import ecom.repository.ProductRepository;
import ecom.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(
                request.name(),
                request.price(),
                request.category(),
                request.stock()
        );
        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                	.orElseThrow(() -> new ResourceNotFoundException("❌ Product not found"));

        product.setName(request.name());
        product.setPrice(request.price());
        product.setCategory(request.category());
        product.setStock(request.stock());

        return toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("❌ Product not found"));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    @Override
	public List<ProductResponse> filterProducts(String category, java.math.BigDecimal maxPrice) {
		return productRepository.findAll().stream()
				.filter(p -> (category == null || p.getCategory().equalsIgnoreCase(category))
						&& (maxPrice == null || p.getPrice().compareTo(maxPrice) <= 0))
				.map(this::toResponse).collect(Collectors.toList());
	}

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getCategory(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
