package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

/**
 * Controller for the menu view
 * 
 * @author Thomas ASTRAND
 *
 */
public class MenuController {
	private ScreensManager screensmanager = ScreensManager.getInstance();
	
	/**
	 * Button to play vs the AI
	 */
	@FXML
	private Button playVsAi;
	
	/**
	 * Button to play vs a Human
	 */
	@FXML
	private Button playVsHuman;
	
	/**
	 * Button to go to the settings
	 */
	@FXML
	private Button settings;
	
	/**
	 * Button to go to quit the app
	 */
	@FXML
	private Button quit;
	
	public MenuController() {
		
	}
	
	public MenuController(ScreensManager scenesmanager) {
		this.screensmanager = scenesmanager;
	}
	
	public void goToGameVsAi(ActionEvent event) {
		String gameType = "Partie contre IA";
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/game.fxml"));
			Parent root = (Parent)loader.load();
			GameController controller = loader.getController();
			controller.setUpperTitle(gameType);
			controller.setGame();
			screensmanager.switchToScene(new Scene(root));
			screensmanager.setStageTitle("Jeu du morpion - Partie contre l'IA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToGameVsHuman(ActionEvent event) {
		String gameType = "Partie contre Homme";
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/game.fxml"));
			Parent root = (Parent)loader.load();
			GameController controller = loader.getController();
			controller.setUpperTitle(gameType);
			controller.setGame();
			screensmanager.switchToScene(new Scene(root));
			screensmanager.setStageTitle("Jeu du morpion - Partie contre Homme");
			controller.rotateGrid();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Quit the app
	 * @param event
	 */
	public void quitApp(ActionEvent event) {
		System.exit(0);
	}

}
