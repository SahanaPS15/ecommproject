package ecom.repository;

import ecom.entity.Order;
import ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByUserId(Long userId);
    // Custom query to fetch an Order along with its items in a single query
    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :orderId")
    Order findByIdWithItems(@Param("orderId") Long orderId);
}

