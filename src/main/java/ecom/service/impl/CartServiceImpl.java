package ecom.service.impl;

import ecom.dto.CartDTO;
import ecom.entity.Cart;
import ecom.entity.CartItem;
import ecom.entity.Product;
import ecom.entity.User;
import ecom.exception.ResourceNotFoundException;
import ecom.mapper.CartMapper;
import ecom.repository.CartRepository;
import ecom.repository.ProductRepository;
import ecom.repository.UserRepository;
import ecom.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CartDTO addToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));

        // Find existing item or create new one
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .orElse(new CartItem(cart, product, 0));

        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        // Ensure item is added only once
        if (!cart.getItems().contains(cartItem)) {
            cart.getItems().add(cartItem);
        }

        // ✅ Recalculate total
        recalculateCartTotal(cart);

        cartRepository.save(cart);

        return CartMapper.toDTO(cart);
    }

    @Override
    public CartDTO updateQuantity(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUser(
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"))
        ).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not in cart"));

        item.setQuantity(quantity);

        // ✅ Recalculate total
        recalculateCartTotal(cart);

        cartRepository.save(cart);

        return CartMapper.toDTO(cart);
    }

    @Override
    public CartDTO viewCart(Long userId, Long productId, int quantity, String action, String direction) {
        // TODO: implement cart view logic
        return null;
    }

    @Override
    public CartDTO removeFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUser(
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"))
        ).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        // ✅ Recalculate total
        recalculateCartTotal(cart);

        cartRepository.save(cart);

        return CartMapper.toDTO(cart);
    }

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUser(
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"))
        ).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        // Ensure total is always updated
        recalculateCartTotal(cart);

        return CartMapper.toDTO(cart);
    }

    // 🔹 Helper method to recalculate cart total
    private void recalculateCartTotal(Cart cart) {
        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotal(total);
    }
}
