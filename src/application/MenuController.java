package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MenuController {
	private ScreensManager scenesmanager = ScreensManager.getInstance();
	
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
		this.scenesmanager = scenesmanager;
	}
	
	public void goToGameVsAi(ActionEvent event) {
		scenesmanager.switchToScene("gameVsAi");
	}

	/**
	 * Quit the app
	 * @param event
	 */
	public void quitApp(ActionEvent event) {
		System.exit(0);
	}

}
