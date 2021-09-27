package model;

import java.io.Serializable;

public class Ingredient implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String quantity;
	private double amount;
	private String measure;
		
	public Ingredient(String name, double amount, String measure) {
		this.name = name;
		this.amount = amount;
		this.measure = measure;
		quantity=amount+" "+measure;
	}
	
	public void editAmount(double newAmount) {
		amount+=newAmount;
		setQuantity(amount+" "+measure);
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
