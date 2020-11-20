package demo.retailstorediscounts.calculations;

import demo.retailstorediscounts.datamodels.Item;

public interface Cart {
    
	public boolean addItem(Item item);
	
	public boolean removeItem(Item item);
	
	public double getAmount();
	
	public void updateAmount(double price);
	
	public void addToDiscount(double amount);
	
	public void removeFromDiscount(double amount);
	
	public double getPaybleAmount();
	
}
