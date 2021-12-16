package model;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.*;

public class Restaurant {
	public static final String DATA_EMPLOYEES="data/employees.emp";
	public static final String DATA_INVENTORY="data/ingredients.inv";
	public static final String DATA_MENU="data/dishes.menu";
	public static final String DATA_DELIVERIES="data/deliveries.order";
	public static final String EMPLOYEES_REPORT="data/employeesReport.txt";
	public static final String DISHES_REPORT="data/dishesReport.txt";
	
	private List<Employee> worker;
	private List <Ingredient> ingredients;
	private List<Dish> menu;
	private List<Delivery> deliveries;
	private List<Dish> actualDelivery;
	
	
	
	public Restaurant() {
		setWorker(new ArrayList<Employee>());
		ingredients= new ArrayList<Ingredient>();
		menu = new ArrayList<Dish>();
		actualDelivery= new ArrayList<Dish>();
		deliveries = new ArrayList<Delivery>();
		worker.add(new Employee("juan","contraseña","123456",LocalDate.of(2002, 03, 23)));
	}

	public void saveEmployees() throws FileNotFoundException, IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(DATA_EMPLOYEES) );
		oos.writeObject(worker);
		oos.close();
	}
	
	public void saveInventory() throws FileNotFoundException, IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(DATA_INVENTORY) );
		oos.writeObject(ingredients);
		oos.close();
	}
	
	public void saveDishes() throws FileNotFoundException, IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(DATA_MENU) );
		oos.writeObject(menu);
		oos.close();
	}
	
	public void saveDeliveries() throws FileNotFoundException, IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(DATA_DELIVERIES) );
		oos.writeObject(deliveries);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadEmployees() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f=new File(DATA_EMPLOYEES);
		//boolean isLoadIt=false;
		if (f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			worker=(List<Employee>) ois.readObject();
			//isLoadIt=true;
			ois.close();
		}
		//return isLoadIt;
	}
	
	@SuppressWarnings("unchecked")
	public void loadIngredients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f=new File(DATA_INVENTORY);
		//boolean isLoadIt=false;
		if (f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			ingredients=(List<Ingredient>) ois.readObject();
			//isLoadIt=true;
			ois.close();
		}
		//return isLoadIt;
	}
	
	@SuppressWarnings("unchecked")
	public void loadDishes() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f=new File(DATA_MENU);
		//boolean isLoadIt=false;
		if (f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			menu=(List<Dish>) ois.readObject();
			//isLoadIt=true;
			ois.close();
		}
		//return isLoadIt;
	}
	
	@SuppressWarnings("unchecked")
	public void loadDeliveries() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f=new File(DATA_DELIVERIES);
		//boolean isLoadIt=false;
		if (f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			deliveries=(List<Delivery>) ois.readObject();
			//isLoadIt=true;
			ois.close();
		}
		//return isLoadIt;
	}
	

	public void exportEmployeesReport() throws IOException {
		FileWriter fw = new FileWriter(EMPLOYEES_REPORT, false);
		for(int i=0; i<worker.size();i++) {
			Employee myWorker= worker.get(i);
			fw.write(myWorker.generateReport()+"\n");
		}
		
		fw.close();
	}
	public void exportDishesReport() throws IOException {
		FileWriter fw = new FileWriter(DISHES_REPORT, false);
		for(int i=0; i<menu.size();i++) {
			Dish myDish= menu.get(i);
			fw.write(myDish.generateReport()+"\n");
		}
		fw.close();
	}
	
	public boolean verification(String id, String password) {
		boolean condition=false;
		for(int i=0;i<worker.size()&!condition;i++) {
			if(id.equalsIgnoreCase(worker.get(i).getId())&&password.equals(worker.get(i).getPassword())) {
				condition=true;
								
			}
		}
		return condition;
	}
	
	public boolean changePassword(String idR, String password) {
		boolean done = false;
		for(int i=0; i<worker.size()&!done; i++) {
			if(idR.equalsIgnoreCase(worker.get(i).getId())) {
				worker.get(i).setPassword(password);
				done = true;
			}
		}
		return done;
	}
	
	public boolean addWorker(Employee newEmployee) throws FileNotFoundException, IOException {
		for(int i=0;i<worker.size();i++) {
			if(newEmployee.getId().equalsIgnoreCase(worker.get(i).getId())) {
				return false;
			}
		}
		if(worker.add(newEmployee)) {
			saveEmployees();
			return true;
		}
		return false;
	}

	
	public int addIngrendient(Ingredient newIngredient) throws FileNotFoundException, IOException {
		int action=0;
		boolean conti=true;
		for(int i=0;i<ingredients.size()&&conti;i++) {
			if(ingredients.get(i).getName().equalsIgnoreCase(newIngredient.getName())) {
				ingredients.get(i).editAmount(newIngredient.getAmount());
				ingredients.get(i).setMeasure(newIngredient.getMeasure());
				saveInventory();
				conti=false;
				return 1;
			}
		}
		if (ingredients.add(newIngredient)&&conti) {
			saveInventory();
			return 2;
		}
		
		return action;
	}
	
	public void deleteIngredient(String nameIngredient) {
		for(int i=0;i<ingredients.size();i++) {
			if(ingredients.get(i).getName().equalsIgnoreCase(nameIngredient)) {
				ingredients.remove(i);
			}
		}
	}
	
	public boolean createCombo(String name,double price ,boolean isAdded) throws FileNotFoundException, IOException {
		for(int i=0; i<menu.size() && isAdded==false; i++) {
			if(name.equals(menu.get(i).getName())){
				isAdded = true;				
			}					
		}
		if(isAdded==false) {
			menu.add(new Dish(name, price));
			saveDishes();
		}
		return isAdded;
		
	}
	
	public boolean addRecipe(String name, String ingredient, double quantity ) {
		boolean found = true;
		int position = 0;
		String measure="";
		for(int i=0; i<menu.size() && found; i++) {
			if(name.equals(menu.get(i).getName())){
				found = false;
				position = i;								
			}					
		}
		
		for(int i=0;i<ingredients.size();i++) {
			if(ingredients.get(i).getName().equalsIgnoreCase(ingredient)) {
				measure=ingredients.get(i).getMeasure();
			}
		}
		
		for(int i=0; i<menu.get(position).getRecipe().size();i++) {
			if(menu.get(position).getRecipe().get(i).getName().equals(ingredient)) {
				
				return false;
			}
		}
		return menu.get(position).addRecipe(ingredient, quantity, measure);
	}
	
	public Dish searchCombo(String name) {
		boolean found = false;
		
		Dish dish=null;
		for(int i=0; i<menu.size() && !found; i++) {
			if(name.equals(menu.get(i).getName())){
				found = true;
				dish = menu.get(i);
			}					
		}
		return dish;
		
	}
	
	public boolean verifyAmount(Dish comboName) {
		
		boolean done = true;
		double num=0;
		for(int i=0; i<comboName.getRecipe().size()&&done; i++) {
			for(int j=0; j<ingredients.size(); j++) {
				if((comboName.getRecipe().get(i).getName().equalsIgnoreCase(ingredients.get(j).getName()))){
					num = ((ingredients.get(j).getAmount())-(comboName.getRecipe().get(i).getAmount()));
					if(num<0) {
						done = false;

					}
					else {
						ingredients.get(j).setAmount(num);
					}
				}
			}
		}

		return done;
	}
	
	public boolean deleteComboFromDelivery(String name) {
		boolean deleted = false;
		boolean found = false;
		Dish dish = null;
		for(int i=0; i<menu.size() && !found; i++) {
			if(name.equals(menu.get(i).getName())){
				found = true;
				dish = menu.get(i);
			}		
		}		
		for(int i=0; i<dish.getRecipe().size()&&deleted; i++) {
			for(int j=0; j<ingredients.size(); j++) {
				if((dish.getRecipe().get(i).getName().equalsIgnoreCase(ingredients.get(j).getName()))){
					ingredients.get(j).setAmount((ingredients.get(j).getAmount())+(dish.getRecipe().get(i).getAmount()));

					System.out.println("Entre al condicional"+dish.getName());
					deleted = true;		
					
				}
			}
		}

		return deleted;
		
	}
	
	public int addToDelivery(int deliveryNum, String value) {		
		int newNum = 0;
		deliveries.get(deliveryNum).addOrder(getActualDelivery());
		deliveries.get(deliveryNum).setCode(value);
		newNum++;
		return newNum;
		
	}
	
	public String findOrders(String code) {
		String message="";
		boolean found=false;
		int position=0;
		for(int i=0; i<deliveries.size() && !found; i++) {
			if(code.equals(deliveries.get(i).getCode())){
				found = true;
				position = i;								
			}					
		}
		
		for(int i=0; i<deliveries.get(position).getOrder().size();i++) {
			message+=deliveries.get(position).getOrder().get(i).getName()+"_ _ _ _ _ _ _ _ _ "+deliveries.get(position).getOrder().get(i).getCost()+"\n";

			System.out.println(message);
		}
		return message;
	}
	
	public boolean updateDeliveryState(String cbCode, String cbState) {
		boolean update = false;
		for(int i=0; i<deliveries.size(); i++) {
			if(cbCode.equalsIgnoreCase(deliveries.get(i).getCode())){
				deliveries.get(i).setDeliveryState(cbState);
				update = true;
			}
		}
		
		return update;
	}

	public List<Employee> getWorker() {
		return worker;
	}

	public void setWorker(List<Employee> worker) {
		this.worker = worker;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	public List<Dish> getMenu() {
		return menu;
	}


	public List<Delivery> getDeliveries() {
		return deliveries;
	}


	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}


	public List<Dish> getActualDelivery() {
		return actualDelivery;
	}
	
	
	
	
	
	
	
	
	
}
