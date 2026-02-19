package ecom.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ecom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	 // Filtering by category
    List<Product> findByCategoryIgnoreCase(String category);

    // Filtering by price less than or equal
    List<Product> findByPriceLessThanEqual(BigDecimal price);

    // Filtering by category and price
    List<Product> findByCategoryIgnoreCaseAndPriceLessThanEqual(String category, BigDecimal price);
    
}
