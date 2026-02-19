package ecom.controller;

import ecom.dto.CartDTO;
import ecom.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add product to cart
    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<CartDTO> addToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

    // Update product quantity in cart
    @PutMapping("/{userId}/update/{productId}")
    public ResponseEntity<CartDTO> updateQuantity(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.ok(cartService.updateQuantity(userId, productId, quantity));
    }

	// Remove product from cart
	@DeleteMapping("/{userId}/remove/{productId}")
	public ResponseEntity<CartDTO> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
		return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
	}

	// Get cart details
	@GetMapping("/view/{userId}")
	public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
		return ResponseEntity.ok(cartService.getCartByUserId(userId));
	}
}
