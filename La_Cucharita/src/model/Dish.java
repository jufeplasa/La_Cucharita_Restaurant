package model;

import java.util.ArrayList;
import java.util.List;

public class Dish {
	
	private List<Ingredient> ingredients;
	
	private String name;
	private String amount;
	private int price;
	
	
	
	public Dish(String name, String amount, int price) {
		
		ingredients = new ArrayList<Ingredient>();
		this.name = name;
		this.amount = amount;
		this.price = price;
		
		
		
	}



	public List<Ingredient> getIngredients() {
		return ingredients;
	}



	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
