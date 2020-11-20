package demo.retailstorediscounts.datamodels;

public interface Item {
      
	public double getTotalPrice();
	public void setQuantity(double quantity);
	public void setPrice(double price);
	public void setQuantityType(QuantityType quantityType);
	public ProductType getProductType();
	public String getName();
	public String getId();
	public double getQuantity();
	public double getPrice();
	public QuantityType getQuantityType();
	
}
