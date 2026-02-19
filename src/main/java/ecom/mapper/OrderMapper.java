package ecom.mapper;

import ecom.dto.OrderItemDTO;
import ecom.dto.OrderResponse;
import ecom.dto.ProductResponse;
import ecom.entity.Order;
import ecom.entity.OrderItem;
import ecom.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponse toResponse(Order order) {
        List<OrderItemDTO> items = order.getItems().stream()
                .map(OrderMapper::mapOrderItem)
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                items,
                order.getSubtotal(),
                order.getTax(),
                order.getDiscount(),
                order.getTotal(),
                order.getStatus() != null ? order.getStatus().name() : "UNKNOWN"
        );
    }

    private static OrderItemDTO mapOrderItem(OrderItem item) {
        Product product = item.getProduct();

        ProductResponse productResponse = null;
        if (product != null) {
            productResponse = new ProductResponse(
                    product.getId(),
                    product.getCategory(),
                    product.getName(),
                    product.getPrice(),  
                    product.getStock()
            );
        }

        return new OrderItemDTO(
                item.getId(),
                productResponse,
                item.getQuantity(),
                item.getPrice()
        );
    }
}


