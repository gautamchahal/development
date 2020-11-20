package demo.retailstorediscounts.datamodels;

public class Product implements Item{
	
	private String name;
	
	private String id;
	
	private ProductType productType;
	
	private double price;
	
	private double quantity;
	
	private QuantityType quantityType;
	
	
	
	
	public Product(String name, String id, ProductType productType){
		this.name = name;
		this.id = id;
		this.productType = productType;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	@Override
	public ProductType getProductType() {
		return productType;
	}

	@Override
	public double getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public double getTotalPrice() {
		return price*quantity;
	}

	@Override
	public void setQuantityType(QuantityType quantityType) {
		this.quantityType = quantityType;
	}

	@Override
	public QuantityType getQuantityType() {
		return quantityType;
	}

}
