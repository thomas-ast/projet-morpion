package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * Launches the application
 * @author thomas
 */
public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Initializing the scene manager 
			ScreensManager screensmanager = ScreensManager.getInstance();
			
			// Main Menu
			AnchorPane rootMainMenu = (AnchorPane)FXMLLoader.load(getClass().getResource("views/main_menu.fxml"));
			String cssMainMenu = getClass().getResource("views/main_menu.css").toExternalForm();
			Scene mainMenu = new Scene(rootMainMenu, 1280, 720);
			screensmanager.addScene("mainMenu", mainMenu, cssMainMenu);
			
			screensmanager.init(primaryStage, mainMenu); // will start with main menu scene
			
			// Game vs Ai
			AnchorPane rootGameVsAi = (AnchorPane)FXMLLoader.load(getClass().getResource("views/game.fxml"));
			String cssGameVsAi = getClass().getResource("views/game.css").toExternalForm();
			Scene gameVsAi = new Scene(rootGameVsAi, 1280, 720);
			screensmanager.addScene("gameVsAi", gameVsAi, cssGameVsAi);
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
