package model;

public class CreditCard {
	
	private int id;
	private double usedAmount;
	
	public CreditCard(int id, double usedAmount) {
		this.id = id;
		this.usedAmount = usedAmount;
	}

	//getters
	public int getId() {
		return id;
	}

	public double getUsedAmount() {
		return usedAmount;
	}

	//setters
	public void setUsedAmount(double usedAmount) {
		this.usedAmount = usedAmount;
	}
}
