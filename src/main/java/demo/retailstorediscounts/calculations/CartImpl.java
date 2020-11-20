package demo.retailstorediscounts.calculations;

import java.util.List;

import demo.retailstorediscounts.datamodels.Item;
import demo.retailstorediscounts.datamodels.ProductType;

public class CartImpl implements Cart{
	
	private List<Item> cartItems;
	
	private double totalAmount=0;
	
	private double percentageBasedDiscount=0;
	
	private Discounts discount;
	
	public CartImpl(Discounts discount, List<Item> cartItems){
		this.discount = discount;
		this.cartItems = cartItems;
	}

	public double getTotalDiscount() {
		return percentageBasedDiscount+discount.amountBasedDiscount(totalAmount);
	}

	@Override
	public boolean addItem(Item item) {
		cartItems.add(item);
		updateAmount(item.getTotalPrice());
		if(!item.getProductType().equals(ProductType.GROCERY)) {
		   addToDiscount(item.getTotalPrice());
		}
		return true;
	}

	@Override
	public boolean removeItem(Item item) {
		cartItems.remove(item);
		updateAmount(-1*item.getTotalPrice());
		removeFromDiscount(item.getTotalPrice());
		return true;
	}

	@Override
	public double getAmount() {
		return totalAmount;
	}
	
	@Override
	public void updateAmount(double price) {
		totalAmount+=price;
	}
	
	@Override
	public void addToDiscount(double price) {
		percentageBasedDiscount+=discount.percentageBasedDiscount(price);
	}
	
	@Override
	public void removeFromDiscount(double price) {
		percentageBasedDiscount-=discount.percentageBasedDiscount(price);
	}
	
	@Override
	public double getPaybleAmount() {
		
		return totalAmount-getTotalDiscount();
	}
	
}
