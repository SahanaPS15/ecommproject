package ecom.service;

import ecom.dto.CartDTO;

public interface CartService {
    CartDTO addToCart(Long userId, Long productId, int quantity);
    CartDTO updateQuantity(Long userId, Long productId, int quantity);
   CartDTO viewCart(Long userId, Long productId, int quantity, String action, String direction);
   CartDTO removeFromCart(Long userId, Long productId);
   CartDTO getCartByUserId(Long userId);
   
}

