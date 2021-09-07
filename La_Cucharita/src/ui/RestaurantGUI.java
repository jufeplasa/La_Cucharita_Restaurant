package ui;
import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Employee;
import model.Restaurant;

public class RestaurantGUI {
	
	private Stage mainStage;

	@FXML
	private Pane mainPane;

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
    private TableColumn<Employee, String> tcName;

    @FXML
    private TableColumn<Employee, String> tcID;

    @FXML
    private TableColumn<Employee, LocalDate> tcBirthday;
	
	private Restaurant restaurant;
	
	private ObservableList<Employee> observableList;
	
	private boolean registered;
	
	public RestaurantGUI() {
		restaurant= new Restaurant();
	}
	
	public void initializeTableView() {
		observableList= FXCollections.observableArrayList(restaurant.getWorker());
    	tableView.setItems(observableList);
    	tcName.setCellValueFactory(new PropertyValueFactory<Employee,String>("Name"));
    	tcID.setCellValueFactory(new PropertyValueFactory<Employee,String>("Id"));
    	tcBirthday.setCellValueFactory(new PropertyValueFactory<Employee,LocalDate>("Birthday"));
	}

	@FXML
	public void logIn(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("OptionMenu.fxml"));
		String id= txtId.getText();
		String password=passwordField.getText();
		boolean condition=restaurant.verification(id, password);
		if(condition) {
			setRegistered(true);
			fxmlLoader.setController(this);
			Parent root= fxmlLoader.load();
			Scene scene= new Scene(root);
			mainStage.setScene(scene);
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
		Scene scene= new Scene(root);
		mainStage.setScene(scene);
		mainStage.show();
	}

	@FXML
	public void logout(ActionEvent event) throws IOException {
		setRegistered(false);
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent root= fxmlLoader.load();
		Scene scene= new Scene(root);
		mainStage.setScene(scene);
		mainStage.show();
	}

	@FXML
	public void showEmployeeList(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("EmployeeList.fxml"));
		fxmlLoader.setController(this);
		Parent window= fxmlLoader.load();
		mainPane.getChildren().setAll(window);
		initializeTableView();
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
			Scene scene= new Scene(root);
			mainStage.setScene(scene);
			mainStage.show();
		}
		else {
			FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Login.fxml"));
			fxmlLoader.setController(this);
			Parent root= fxmlLoader.load();
			Scene scene= new Scene(root);
			mainStage.setScene(scene);
			mainStage.show();
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

	public ObservableList<Employee> getObservableList() {
		return observableList;
	}

	public void setObservablelist(ObservableList<Employee> observableList) {
		this.observableList = observableList;
	}
	



}
