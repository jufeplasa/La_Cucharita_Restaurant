package ui;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Employee;
import model.Ingredient;
import model.Restaurant;

public class RestaurantGUI {

	private Stage mainStage;

	@FXML
	public BorderPane mainPane;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtname;

	@FXML
	private TextField txtId2;

	@FXML
	private PasswordField txtpassword2;

	@FXML
	private DatePicker datePicker;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TableView<Employee> tableView;

	@FXML
	private TableView<Ingredient> ingredientsTable;

	@FXML
	private TableColumn<Employee, String> tcName;

	@FXML
	private TableColumn<Employee, String> tcID;

	@FXML
	private TableColumn<Employee, LocalDate> tcBirthday;

	@FXML
	private TableView<Ingredient> tableViewInventory;

	@FXML
	private TableColumn<Ingredient, String> tbNameIngredient;

	@FXML
	private TableColumn<Ingredient, String> tbQuantityIngredient;

	@FXML
	private ComboBox<String> cbIngredient;

	@FXML
	private TextField ingredientName;

	@FXML
	private TextField quantity;

	@FXML
	private ComboBox<String> cbMeasure;

	private Restaurant restaurant;

	private String mr;

	private String ingredientSelect;


	private boolean registered;

	public RestaurantGUI() {
		restaurant= new Restaurant();
	}

	public void initializeTableViewEmployees() {
		ObservableList<Employee> observableList;
		observableList= FXCollections.observableArrayList(restaurant.getWorker());
		tableView.setItems(observableList);
		tcName.setCellValueFactory(new PropertyValueFactory<Employee,String>("Name"));
		tcID.setCellValueFactory(new PropertyValueFactory<Employee,String>("Id"));
		tcBirthday.setCellValueFactory(new PropertyValueFactory<Employee,LocalDate>("Birthday"));
	}

	public void initializeTableViewIngredients() {
		ObservableList<Ingredient> observableList;
		observableList= FXCollections.observableArrayList(restaurant.getIngredients());
		tableViewInventory.setItems(observableList);
		tbNameIngredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("Name"));
		tbQuantityIngredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("quantity"));
	}

	@FXML
	public void loadLogIn() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(root);
		mainStage.close();
		mainStage.show();
	}

	@FXML
	public void logIn(ActionEvent event) throws IOException {
		String id= txtId.getText();
		String password=passwordField.getText();
		boolean condition=restaurant.verification(id, password);
		if(condition) {

			setRegistered(true);
			FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("OptionMenu.fxml"));
			fxmlLoader.setController(this);
			Parent root= fxmlLoader.load();
			FXMLLoader fxmlLoader2= new FXMLLoader(getClass().getResource("CoverPage.fxml"));
			fxmlLoader2.setController(this);
			Parent root2= fxmlLoader2.load();
			mainPane.getChildren().clear();
			mainPane.setTop(root);
			mainPane.setCenter(root2);
			mainStage.close();
			mainStage.show();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Login Error");
			alert.setHeaderText("The id or the password are not correct");
			alert.setContentText("Please try again or create a count");
			alert.showAndWait();
		}
	}


	@FXML
	public void register(ActionEvent Event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Register.fxml"));
		fxmlLoader.setController(this);
		Parent root= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(root);
		mainStage.close();
		mainStage.show();
	}

	@FXML
	public void logout(ActionEvent event) throws IOException {
		setRegistered(false);
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent root= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(root);
		mainStage.close();
		mainStage.show();
	}

	@FXML
	public void showEmployeeList(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("EmployeeList.fxml"));
		fxmlLoader.setController(this);
		Parent root= fxmlLoader.load();
		FXMLLoader fxmlLoader2= new FXMLLoader(getClass().getResource("OptionMenu.fxml"));
		fxmlLoader2.setController(this);
		Parent root2= fxmlLoader2.load();
		mainPane.getChildren().clear();
		mainPane.setTop(root2);
		mainPane.setCenter(root);
		mainStage.close();
		mainStage.show();		
		initializeTableViewEmployees();
	}  

	@FXML
	public void createAnEmployee(ActionEvent event) {
		Alert alert = new Alert(null);
		String name=txtname.getText();
		String id=txtId2.getText();
		String password=txtpassword2.getText();
		LocalDate birthday= datePicker.getValue();
		if((name==null)||(id==null)||(password==null)||(birthday==null)) {
			alert.setAlertType(AlertType.WARNING);
			alert.setTitle("Problems with create employee");
			alert.setHeaderText("There are missing information");
			alert.setContentText("Please complete all the blank spaces");
			alert.showAndWait();
		}
		else {
			boolean condition=restaurant.addWorker(new Employee(name, password, id, birthday));
			if(condition){
				alert.setAlertType(AlertType.INFORMATION);
				alert.setTitle("Successful  action");
				alert.setHeaderText("WELCOME "+name);
				alert.setContentText("The employee has added successful.");
				alert.showAndWait();
				txtname.setText(null);
				txtId2.setText(null);
				txtpassword2.setText(null);
				datePicker.setValue(null);;
			}
			else {
				alert.setAlertType(AlertType.WARNING);
				alert.setTitle("Problems with create employee");
				alert.setHeaderText("There are a problem with information");
				alert.setContentText("the id is already exist");
				alert.showAndWait();
				txtId2.setText(null);
			}
		}

	}

	@FXML
	public void back(ActionEvent event) throws IOException {
		if(isRegistered()) {

			FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("OptionMenu.fxml"));
			fxmlLoader.setController(this);
			Parent root= fxmlLoader.load();
			FXMLLoader fxmlLoader2= new FXMLLoader(getClass().getResource("CoverPage.fxml"));
			fxmlLoader2.setController(this);
			Parent root2= fxmlLoader2.load();
			mainPane.getChildren().clear();
			mainPane.setTop(root);
			mainPane.setCenter(root2);
			mainStage.close();
			mainStage.show();
		}
		else {

			FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("login.fxml"));
			fxmlLoader.setController(this);
			Parent root= fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(root);
			mainStage.close();
			mainStage.show();
		}
	}

	@FXML
	public void manageInventory(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("ManageInventory.fxml"));
		fxmlLoader.setController(this);
		Parent root= fxmlLoader.load();
		FXMLLoader fxmlLoader2= new FXMLLoader(getClass().getResource("OptionMenu.fxml"));
		fxmlLoader2.setController(this);
		Parent root2= fxmlLoader2.load();
		mainPane.getChildren().clear();
		mainPane.setTop(root2);
		mainPane.setCenter(root);
		showOptions();
		showOptionIngredients();
		quantity.setText("0");
	}

	@FXML
	public void showIngredientList(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Inventory.fxml"));
		fxmlLoader.setController(this);
		Parent root= fxmlLoader.load();
		FXMLLoader fxmlLoader2= new FXMLLoader(getClass().getResource("OptionMenu.fxml"));
		fxmlLoader2.setController(this);
		Parent root2= fxmlLoader2.load();
		mainPane.getChildren().clear();
		mainPane.setTop(root2);
		mainPane.setCenter(root);
		initializeTableViewIngredients();
	}

	@FXML
	public void addIngredient(ActionEvent event) {
		Alert alert=new Alert(null);
		double amount=0;
		String ingredient=ingredientName.getText();
		amount=Double.parseDouble(quantity.getText());
		if(ingredient==null||amount==0||mr==null) {
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("You have not complete the register");
			alert.setContentText("You have to complete all the information for add the ingredient");
			alert.showAndWait();
		}
		int option=restaurant.addIngrendient(new Ingredient(ingredient, amount, mr));
		if (option==1) {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Ingredient Update");
			alert.setContentText("The ingredient has updated successfully");
			alert.showAndWait();
		}
		else if(option==2) {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("New ingredient added");
			alert.setContentText("The ingredient has added successfully");
			alert.showAndWait();
		}
	}

	public void showOptions() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll("ml","g","kg","unds");
		cbMeasure.getItems().addAll(items);
		cbMeasure.setOnAction(new EventHandler<ActionEvent>() {     
			public void handle(ActionEvent e)  {    
				setMr(cbMeasure.getValue()+"");
			}       
		});
	}

	public void showOptionIngredients() {
		ObservableList<String> items = FXCollections.observableArrayList();
		for(int i=0;i<restaurant.getIngredients().size();i++) {
			items.add(restaurant.getIngredients().get(i).getName());
		}
		cbIngredient.getItems().addAll(items);
		cbIngredient.setOnAction(new EventHandler<ActionEvent>() {     
			public void handle(ActionEvent e)  {    
				setIngredientSelect(cbIngredient.getValue());
				
			}       
		});
	}

	@FXML
	public void deleteIngredient(ActionEvent event) {
		Alert alert = new Alert(null);
		if(ingredientSelect!=null) {
			alert.setAlertType(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("You are going to delete an ingredient");
			alert.setContentText("Are you ok with this?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				restaurant.deleteIngredient(ingredientSelect);
				cbIngredient.setValue(null);
			}
		}
		else {
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("There isn't anything to delete");
			alert.setContentText("Please select an ingredient to delete it");
			alert.showAndWait();
		}
	}


	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public String getMr() {
		return mr;
	}

	public void setMr(String mr) {
		this.mr = mr;
	}

	public String getIngredientSelect() {
		return ingredientSelect;
	}

	public void setIngredientSelect(String ingredientSelect) {
		this.ingredientSelect = ingredientSelect;
	}


}
