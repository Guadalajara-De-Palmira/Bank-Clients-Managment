package model;

public class Account {
	
	private int id;
	private double savings;
	private boolean active;
	public String retirementReason;

	public Account(int id, double savings) {
		this.id = id;
		this.savings = savings;
		this.active = true;
		this.retirementReason = "";
	}

	//getters
	public int getId() {
		return id;
	}

	public double getSavings() {
		return savings;
	}

	public boolean isActive() {
		return active;
	}
	
	//setters
	public void setSavings(double savings) {
		this.savings = savings;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public String getRetirementReason() {
		return retirementReason;
	}

	public void setRetirementReason(String retirementReason) {
		this.retirementReason = retirementReason;
	}
}