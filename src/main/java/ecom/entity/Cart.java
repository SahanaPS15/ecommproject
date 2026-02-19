package ecom.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    private BigDecimal total;
    public Cart() {
		this.items = new ArrayList<>(); // ✅ safety
		this.total = BigDecimal.ZERO; // ✅ safety
    }

    // ✅ Custom constructor
    public Cart(User user) {
        this.user = user;
        this.items = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }
    
   // getters, setters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
