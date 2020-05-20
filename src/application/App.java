package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


/**
 * Launches the application
 * 
 */
public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Initializing the scene manager 
			ScreensManager screensmanager = ScreensManager.getInstance();
			
			// Main Menu screen
			AnchorPane rootMainMenu = (AnchorPane)FXMLLoader.load(getClass().getResource("models/main_menu.fxml"));
			
			screensmanager.init(primaryStage, new Scene(rootMainMenu, 1280, 720)); // will start with main menu scene
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
