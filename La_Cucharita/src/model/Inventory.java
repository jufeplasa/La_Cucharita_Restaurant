package model;

import java.util.ArrayList;
import java.util.List;


public class Inventory {
	
	private List <Ingredient> ingredients;
	
	public Inventory() {
		ingredients= new ArrayList<Ingredient>();
			
		
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
