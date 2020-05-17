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
			ScreensManager scenesmanager = ScreensManager.getInstance();
			scenesmanager.init(primaryStage); // will start with main menu scene
			
			// Adding and referencing all the scenes of the app
			scenesmanager.addScene("mainMenu", FXMLLoader.load(getClass().getResource("views/main_menu.fxml")), getClass().getResource("views/main_menu.css").toExternalForm());
			scenesmanager.addScene("gameVsAi", FXMLLoader.load(getClass().getResource("views/game.fxml")), getClass().getResource("views/game.css").toExternalForm());
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
