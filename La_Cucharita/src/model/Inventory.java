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
	
	public void addIngrendient(String name, double amount, String measure) {
		boolean condition=true;
		for (int i=0; i<ingredients.size();i++) {
			if (name.equalsIgnoreCase(ingredients.get(i).getName())&& measure.equalsIgnoreCase(ingredients.get(i).getMeasure())){
				ingredients.get(i).editAmount(amount);
				condition=false;
			}
		}
		if (condition) {
			ingredients.add(new Ingredient( name,  amount,  measure));
		}
	}

}
