package model;

public class Ingredient {
	
	private String name;
	private double amount;
	private String measure;
		
	public Ingredient(String name, double amount, String measure) {
		this.name = name;
		this.amount = amount;
		this.measure = measure;
	}
	
	public void editAmount(double newAmount) {
		double result;
		result=amount+newAmount;
		setAmount(result);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	
	

}
