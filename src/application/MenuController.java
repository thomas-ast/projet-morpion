package application;

import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {
	
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
	
	
	/**
	 * Quit the app
	 * @param event
	 */
	public void quitApp(ActionEvent event) {
		System.exit(0);
	}
	
	
}
