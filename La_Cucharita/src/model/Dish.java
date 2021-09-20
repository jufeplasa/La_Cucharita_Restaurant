package model;

import java.util.ArrayList;
import java.util.List;

public class Dish {
	
	private List<Ingredient> recipe;
	
	private String name;
	private double price;
	
	
	
	public Dish(String name, double price) {
		
		recipe= new ArrayList<Ingredient>();
		this.name = name;
		this.price = price;
		
		
		
	}
	
	public void addRecipe(String ingredient, double quantity, String measure ) {
		recipe.add(new Ingredient(ingredient, quantity,  measure));
	}



	public List<Ingredient> getRecipe() {
		return recipe;
	}



	public void setRecipe(List<Ingredient> recipe) {
		this.recipe = recipe;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	
	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
