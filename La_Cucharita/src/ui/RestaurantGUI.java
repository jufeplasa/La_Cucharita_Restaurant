package ui;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Restaurant;

public class RestaurantGUI {
	
	private Stage mainStage;

	@FXML
	private Pane mainPane;

	@FXML
	private TextField txtId;

	@FXML
	private PasswordField passwordField;
	
	private Restaurant restaurant;
	
	public RestaurantGUI() {
		restaurant= new Restaurant();
	}

	@FXML
	public void logIn(ActionEvent event) throws IOException {
		String id= txtId.getText();
		String password=passwordField.getText();
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("OptionMenu.fxml"));
		fxmlLoader.setController(this);
		Parent root= fxmlLoader.load();
		Scene scene= new Scene(root);
		mainStage.setScene(scene);
		mainStage.show();
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
	public void returnToLogin(ActionEvent event) throws IOException {
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



}
