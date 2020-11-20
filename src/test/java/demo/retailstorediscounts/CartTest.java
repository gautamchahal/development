package demo.retailstorediscounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import demo.retailstorediscounts.calculations.Cart;
import demo.retailstorediscounts.calculations.CartImpl;
import demo.retailstorediscounts.calculations.Discounts;
import demo.retailstorediscounts.calculations.DiscountsImpl;
import demo.retailstorediscounts.datamodels.Item;
import demo.retailstorediscounts.datamodels.Product;
import demo.retailstorediscounts.datamodels.ProductType;
import demo.retailstorediscounts.datamodels.QuantityType;
import demo.retailstorediscounts.datamodels.UserInfo;
import demo.retailstorediscounts.datamodels.UserType;

public class CartTest {

	private Cart cart;
	private UserInfo userInfo; 
	private LocalDateTime accountCreationDate;
	
	@Before
	public void setup() {
		accountCreationDate = LocalDateTime.of(2018, 1, 1, 1, 1);
		userInfo = new UserInfo("Test1", accountCreationDate, UserType.EMPLOYEE);
		Discounts discount = new DiscountsImpl(userInfo);
		cart = new CartImpl(discount,new ArrayList<Item>());
	}
	
	@Test
	public void employeeUserDiscountTest() {
		
		Item item1 = new Product("Rice","I12341",ProductType.GROCERY);
		item1.setQuantity(1);
		item1.setQuantityType(QuantityType.KG);
		item1.setPrice(25);
		
		Item item2 = new Product("Cricket Ball","I12342",ProductType.OTHERS);
		item2.setQuantity(1);
		item2.setQuantityType(QuantityType.UNIT);
		item2.setPrice(90);
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		assertSame("I12341",item1.getId());
		assertSame("Rice",item1.getName());
		assertSame("Test1",userInfo.getName());
		assertSame(QuantityType.KG,item1.getQuantityType());
		assertEquals("Item Price", 25, item1.getPrice(), 0);
		assertEquals("Item Quantity", 1, item1.getQuantity(), 0);
		assertEquals("Total Amount ", 115, cart.getAmount(), 0.0);
		assertEquals("Net Payable amount", 83, cart.getPaybleAmount(), 0.0);
		
	}
	
	@Test
	public void affliateUserDiscountTest() {
		userInfo = new UserInfo("Test2", accountCreationDate, UserType.AFFILIATE);
		Discounts discount = new DiscountsImpl(userInfo);
		cart = new CartImpl(discount,new ArrayList<Item>());
		
		Item item1 = new Product("Rice","I12341",ProductType.GROCERY);
		item1.setQuantity(1);
		item1.setQuantityType(QuantityType.KG);
		item1.setPrice(25);
		
		Item item2 = new Product("Cricket Ball","I12342",ProductType.OTHERS);
		item2.setQuantity(1);
		item2.setQuantityType(QuantityType.UNIT);
		item2.setPrice(90);
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		assertEquals("Total Amount ", 115, cart.getAmount(), 0.0);
		assertEquals("Net Payable amount", 101, cart.getPaybleAmount(), 0.0);
		
	}
	
	@Test
	public void userWithMoreThan2YearsDiscountTest() {
		userInfo = new UserInfo("Test3", accountCreationDate, UserType.OTHER);
		Discounts discount = new DiscountsImpl(userInfo);
		cart = new CartImpl(discount,new ArrayList<Item>());
		
		Item item1 = new Product("Rice","I12341",ProductType.GROCERY);
		item1.setQuantity(1);
		item1.setQuantityType(QuantityType.KG);
		item1.setPrice(25);
		
		Item item2 = new Product("Cricket Ball","I12342",ProductType.OTHERS);
		item2.setQuantity(1);
		item2.setQuantityType(QuantityType.UNIT);
		item2.setPrice(90);
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		assertEquals("Total Amount ", 115, cart.getAmount(), 0.0);
		assertEquals("Net Payable amount", 105.5, cart.getPaybleAmount(), 0.0);
		
	}
	
	@Test
	public void userWithLessThan2YearsDiscountTest() {
		accountCreationDate = LocalDateTime.of(2019, 1, 1, 1, 1);
		userInfo = new UserInfo("Test3", accountCreationDate, UserType.OTHER);
		Discounts discount = new DiscountsImpl(userInfo);
		cart = new CartImpl(discount,new ArrayList<Item>());
		
		Item item1 = new Product("Rice","I12341",ProductType.GROCERY);
		item1.setQuantity(1);
		item1.setQuantityType(QuantityType.KG);
		item1.setPrice(25);
		
		Item item2 = new Product("Cricket Ball","I12342",ProductType.OTHERS);
		item2.setQuantity(1);
		item2.setQuantityType(QuantityType.UNIT);
		item2.setPrice(90);
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		assertEquals("Total Amount ", 115, cart.getAmount(), 0.0);
		assertEquals("Net Payable amount", 110, cart.getPaybleAmount(), 0.0);
		
	}
	
	@Test
	public void noDiscountTest() {
		accountCreationDate = LocalDateTime.of(2019, 1, 1, 1, 1);
		userInfo = new UserInfo("Test3", accountCreationDate, UserType.OTHER);
		Discounts discount = new DiscountsImpl(userInfo);
		cart = new CartImpl(discount,new ArrayList<Item>());
		
		Item item1 = new Product("Rice","I12341",ProductType.GROCERY);
		item1.setQuantity(1);
		item1.setQuantityType(QuantityType.KG);
		item1.setPrice(25);
		
		Item item2 = new Product("Cricket Ball","I12342",ProductType.OTHERS);
		item2.setQuantity(1);
		item2.setQuantityType(QuantityType.UNIT);
		item2.setPrice(74);
		
		Item item3 = new Product("Cricket Ball","I12342",ProductType.OTHERS);
		item3.setQuantity(1);
		item3.setQuantityType(QuantityType.UNIT);
		item3.setPrice(74);
		
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		cart.removeItem(item2);
		
		assertEquals("Total Amount ", 99, cart.getAmount(), 0.0);
		assertEquals("Net Payable amount", 99, cart.getPaybleAmount(), 0.0);
		
	}
	
	
}
