package demo.retailstorediscounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import demo.retailstorediscounts.calculations.Discounts;
import demo.retailstorediscounts.calculations.DiscountsImpl;
import demo.retailstorediscounts.datamodels.UserInfo;
import demo.retailstorediscounts.datamodels.UserType;

public class DiscountTest {
	
	private UserInfo userInfo;
	private Discounts discount;
	
	@Before
	public void setup() {
		LocalDateTime accountCreationDate = LocalDateTime.of(2018, 1, 1, 1, 1);
		userInfo = new UserInfo("Test1", accountCreationDate, UserType.EMPLOYEE);
		discount = new DiscountsImpl(userInfo);
	}
	
	@Test
	public void amountBasedDiscountTest() {
		assertEquals("Discount Amount ", 5, discount.amountBasedDiscount(120), 0.0);
		assertEquals("Discount Amount ", 0, discount.amountBasedDiscount(99), 0.0);
		assertEquals("Discount Amount ", 5, discount.amountBasedDiscount(100), 0.0);
		assertEquals("Discount Amount ", 45, discount.amountBasedDiscount(999), 0.0);
	}
	
	@Test
	public void percentageBasedDiscountForEmployee() {
		assertEquals("Percentage Discount For Employee ", 36, discount.percentageBasedDiscount(120), 0.0);
		assertEquals("Percentage Discount For Employee ", 29.7, discount.percentageBasedDiscount(99), 0.0);
		assertEquals("Percentage Discount For Employee ", 30, discount.percentageBasedDiscount(100), 0.0);
		assertEquals("Percentage Discount For Employee ", 299.7, discount.percentageBasedDiscount(999), 0.0);
	}
	
	@Test
	public void percentageBasedDiscountForAffiliate() {
		LocalDateTime accountCreationDate = LocalDateTime.of(2018, 1, 1, 1, 1);
		userInfo = new UserInfo("Test2", accountCreationDate, UserType.AFFILIATE);
		discount = new DiscountsImpl(userInfo);
		assertEquals("Percentage Discount For Affiliate ", 12, discount.percentageBasedDiscount(120), 0.0);
		assertEquals("Percentage Discount For Affiliate ", 9.9, discount.percentageBasedDiscount(99), 0.0);
		assertEquals("Percentage Discount For Affiliate ", 10, discount.percentageBasedDiscount(100), 0.0);
		assertEquals("Percentage Discount For Affiliate ", 99.9, discount.percentageBasedDiscount(999), 0.0);
	}
	
	@Test
	public void percentageBasedDiscountForCustomersOlderThan2Years() {
		LocalDateTime accountCreationDate = LocalDateTime.of(2018, 1, 1, 1, 1);
		userInfo = new UserInfo("Test3", accountCreationDate, UserType.OTHER);
		discount = new DiscountsImpl(userInfo);
		assertEquals("Percentage Discount For Normat User ", 6, discount.percentageBasedDiscount(120), 0.0);
		assertEquals("Percentage Discount For Normat User ", 4.95, discount.percentageBasedDiscount(99), 0.0);
		assertEquals("Percentage Discount For Normat User ", 5, discount.percentageBasedDiscount(100), 0.0);
		assertEquals("Percentage Discount For Normat User ", 49.95, discount.percentageBasedDiscount(999), 0.0);
	}
	
	@Test
	public void percentageBasedDiscountForCustomersLessThan2Years() {
		LocalDateTime accountCreationDate = LocalDateTime.of(2019, 1, 1, 1, 1);
		userInfo = new UserInfo("Test4", accountCreationDate, UserType.OTHER);
		discount = new DiscountsImpl(userInfo);
		assertEquals("Percentage Discount For Less Than 2 Years User ", 0, discount.percentageBasedDiscount(120), 0.0);
		assertEquals("Percentage Discount For Less Than 2 Years User ", 0, discount.percentageBasedDiscount(99), 0.0);
		assertEquals("Percentage Discount For Less Than 2 Years User ", 0, discount.percentageBasedDiscount(100), 0.0);
		assertEquals("Percentage Discount For Less Than 2 Years User ", 0, discount.percentageBasedDiscount(999), 0.0);
	}
	
}
