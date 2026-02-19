package ecom.mapper;

import ecom.dto.CartDTO;
import ecom.dto.CartItemDTO;
import ecom.entity.Cart;
import ecom.entity.CartItem;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        return new CartDTO(
                cart.getId(),
                cart.getUser().getId(),
                cart.getItems().stream()
                        .map(CartMapper::toItemDTO)
                        .collect(Collectors.toList()),
                cart.getTotal()
        );
    }

    private static CartItemDTO toItemDTO(CartItem item) {
        return new CartItemDTO(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                // ✅ Set price from product
                item.getProduct().getPrice()
        );
    }
}
