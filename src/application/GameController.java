package application;

import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameController {
	private ScreensManager screensmanager = ScreensManager.getInstance();
	
	/**
	 * The game grid
	 */
	@FXML
	private Group grid;
	
	/**
	 * The upper left case
	 */
	@FXML
	private Button case_up_left;
	
	/**
	 * The upper mid case
	 */
	@FXML
	private Button case_up_mid;
	
	/**
	 * The upper right case
	 */
	@FXML
	private Button case_up_right;
	
	/**
	 * The middle left case
	 */
	@FXML
	private Button case_mid_left;
	
	/**
	 * The middle mid case
	 */
	@FXML
	private Button case_mid_mid;
	
	/**
	 * The middle right case
	 */
	@FXML
	private Button case_mid_right;
	
	/**
	 * The bottom left case
	 */
	@FXML
	private Button case_bot_left;
	
	/**
	 * The bottom mid case
	 */
	@FXML
	private Button case_bot_mid;
	
	/**
	 * The bottom right case
	 */
	@FXML
	private Button case_bot_right;
	
	
	public void backToMenu(ActionEvent event) {
		screensmanager.switchToScene("mainMenu");
		screensmanager.setStageTitle("Jeu du morpion - Partie contre l'IA");
	}
	
	public void placeMark(ActionEvent event) {
		Button r = (Button)event.getSource();
		r.getStyleClass().add("circle");
		r.applyCss();
	}
	
}
