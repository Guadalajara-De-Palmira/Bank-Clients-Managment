package model;

import java.util.Calendar;

public class Client implements Comparable<Client>,Cloneable{
	private String name;
	private int id;
	private Account account;
	private CreditCard creditCard;
	private String entranceDate;


	private int disabilities;
	
	public Client(String name, int id, Account account, CreditCard creditCard, String entranceDate, int disabilities) {
		this.name = name;
		this.id = id;
		this.account = account;
		this.creditCard = creditCard;
		this.entranceDate = entranceDate;
		this.disabilities = disabilities;
	}
	
	//Getters
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public String getEntranceDate() {
		return entranceDate;
	}

	public int getDisabilities() {
		return disabilities;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setEntranceDate(String entranceDate) {
		this.entranceDate = entranceDate;
	}

	public void setDisabilities(int disabilities) {
		this.disabilities = disabilities;
	}


	@Override
	public int compareTo(Client toCompare) {
		int result = 0;
		if (this.getDisabilities() <= toCompare.getDisabilities()) {
			result  = -1;
		}
		else if (this.getDisabilities() > toCompare.getDisabilities()) {
			result = 1;
		}
		return result;
	}
}