package demo.retailstorediscounts.calculations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import demo.retailstorediscounts.datamodels.UserInfo;
import demo.retailstorediscounts.datamodels.UserType;

public class DiscountsImpl implements Discounts{
	
	private static final double FIVE = 5;
	private static final int THIRTY = 30;
	private static final int TEN = 10;
	private static final int TWO = 2;
	private static final int HUNDRED = 100;
	private static final int YEAR_DAYS = 365;
	private static final int ZERO = 0;
	
	UserInfo userInfo;
	
	public DiscountsImpl(UserInfo userInfo){
		this.userInfo = userInfo;
	}

	@Override
	public double percentageBasedDiscount(double amount) {
		if(userInfo.getUserType().equals(UserType.EMPLOYEE)) {
			return (amount*THIRTY)/HUNDRED;
		}else if(userInfo.getUserType().equals(UserType.AFFILIATE)) {
			return (amount*TEN)/HUNDRED;
		}else if((ChronoUnit.DAYS.between(userInfo.getAccountCreationDate(), LocalDateTime.now())/TWO)>YEAR_DAYS){
			return (amount*FIVE)/HUNDRED;
		}else {
			return ZERO;
		}
		
	}

	@Override
	public double amountBasedDiscount(double amount) {
		return ((int)(amount/HUNDRED))*FIVE;
	}

}
