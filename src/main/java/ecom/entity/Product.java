package ecom.entity;

	import java.math.BigDecimal;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;

	@Entity
	public class Product {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private BigDecimal price;
	    private String category; 
	    private int stock;

	    // Constructors
	    public Product() {}

	    public Product(String name, BigDecimal price,String category, int stock) {
	        this.name = name;
	        this.price = price;
	        this.category = category;
	    }
	    public Product(String name, BigDecimal price, String category) {
	        this(name, price, category, 0); // default stock
	    }

	    public Product(String name, BigDecimal price) {
	        this(name, price, null, 0); // default category + stock
	    }

	    // Getters & Setters
	    public Long getId() { return id; }
	    
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    
	    public BigDecimal getPrice() { return price; }
	    public void setPrice(BigDecimal price) { this.price = price; }
	    
	    public String getCategory() { return category; }
	    public void setCategory(String category) { this.category = category; }

		public int getStock() {return stock;}

		public void setStock(int stock) {this.stock = stock;}
	}
