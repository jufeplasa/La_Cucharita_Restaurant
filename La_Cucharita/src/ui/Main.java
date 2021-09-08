package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private RestaurantGUI restaurantgui;
	
	public Main() {
		restaurantgui = new RestaurantGUI();
	}
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		fxmlLoader.setController(restaurantgui);
		Parent root = fxmlLoader.load();
		restaurantgui.setMainStage(primaryStage);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Restaurant");
		restaurantgui.loadLogIn();
		
		
	}
}
