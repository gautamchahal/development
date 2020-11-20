package demo.retailstorediscounts.datamodels;

import java.time.LocalDateTime;

public class UserInfo {
	
	private String name;
	private LocalDateTime accountCreationDate;
	private UserType userType;
	
	public UserInfo(String name, LocalDateTime accountCreationDate, UserType userType){
		this.name = name;
		this.accountCreationDate = accountCreationDate;
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getAccountCreationDate() {
		return accountCreationDate;
	}

	public UserType getUserType() {
		return userType;
	}
}
