package ecom.entity;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
	public class CartItem {
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne private Cart cart;
	    @ManyToOne private Product product;

	    private int quantity;
	    private BigDecimal price; // subtotal = product.price * quantity
	    public CartItem() {}

	    // ✅ Custom constructor
	    public CartItem(Cart cart, Product product, int quantity) {
	        this.cart = cart;
	        this.product = product;
	        this.quantity = quantity;
	        this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
	    }
        // getters, setters
		public Long getId() {
            return id;
		}

		public void setId(Long id) {
            this.id = id;
        }

	public Cart getCart() {
            return cart;
            }
	            public void setCart(Cart cart) {
	            	            this.cart = cart;
	            	            
	            }
     public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;

		}
		public int getQuantity() {
			return quantity;
			
     }
		public void setQuantity(int quantity) {
			this.quantity = quantity;
			 if (this.product != null) {
			        this.price = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
			 }
		}

		public BigDecimal getPrice() {
            return price;
        }

		public void setPrice(BigDecimal price) {
            this.price = price;
        }
		}
