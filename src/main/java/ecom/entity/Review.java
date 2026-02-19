package ecom.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Product product;
    @ManyToOne private User user;

    private int rating; // 1–5
    private String comment;

    private LocalDateTime createdAt;
    
    public Review() {}
		public Review(User user, Product product, int rating, String comment) {
			this.user = user;
			this.product = product;
			this.rating = rating;
			this.comment = comment;
			this.createdAt = LocalDateTime.now();
	}

		public Long getId() {
        	        return id;
        }
        
		public void setId(Long id) {
			this.id = id;
		}

		public Product getProduct() {
            return product;
        }
		
		public void setProduct(Product product) {
			this.product = product;
		}
		
		public User getUser() {
			return user;
		}
		
		public void setUser(User user) {
			this.user = user;
		}
		
		public int getRating() {
			return rating;
		}
		
		public void setRating(int rating) {
			this.rating = rating;
		}
		
		public String getComment() {
			return comment;
		}
		
		public void setComment(String comment) {
			this.comment = comment;
		}
		
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		
}
