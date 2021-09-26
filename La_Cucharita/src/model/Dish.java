package model;

import java.util.ArrayList;
import java.util.List;

public class Dish {
	
	private List<Ingredient> recipe;
	
	private String name;
	private double price;
	private String cost;
	private String ingredients;
	
	
	public Dish(String name, double price) {
		
		recipe= new ArrayList<Ingredient>();
		this.name = name;
		this.price = price;
		cost="$ "+price;
		ingredients="";
	}
	
	public boolean addRecipe(String ingredient, double quantity, String measure ) {
		boolean done= recipe.add(new Ingredient(ingredient, quantity,  measure));
		setIngredients();
		return done;
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

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients() {
		ingredients="";
		for(int i=0;i<recipe.size();i++) {
		ingredients+=recipe.get(i).getName()+" "+recipe.get(i).getQuantity()+"\n";
		}
	}
	
	

}
