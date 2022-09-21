package entity;

/**
 * Product entity class composed of 5 attributes
 */
public class Product {
	
	private Integer id;
	private String name;
	private String category;
	private Integer price;
	private String active;
	
	public Product() {
	}
	public Product(Integer id, String name, String category, Integer price, String active) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.active = active;
	}
	public Product(String name, String category, Integer price, String active) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.active = active;
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
}