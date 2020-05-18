package application;

import java.io.IOException;

import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
	
	/**
	 * The text above the grid stating which player has to play
	 */
	@FXML
	private Text turnText;
		
	/**
	 * The text under the grid stating which player won
	 */
	@FXML
	private Text winnerText;
	
	/**
	 * The title on the upper left of the screen
	 */
	@FXML
	private Text upperTitle;
	
	/**
	 * Instance of a game
	 */
	private Game game;
	
	
	/**
	 * Brings back to the main menu
	 * @param event
	 */
	public void backToMenu(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/main_menu.fxml"));
			Parent root = (Parent)loader.load();;
			screensmanager.switchToScene(new Scene(root));
			screensmanager.setStageTitle("Jeu du morpion - Menu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Places a mark where the player clicked
	 * @param event
	 */
	public void placeMark(ActionEvent event) {
		Button r = (Button)event.getSource();
		
		if(game.getCurrent_player()=='O')
		{
			r.getStyleClass().add("circle");
			r.applyCss();
		}
		else
		{
			r.getStyleClass().add("cross");
			r.applyCss();
		}
		
		game.checkWin();
		
		if(game.getIs_over()==true)
		{
			//winnerText.setText("Vainqueur: " + game.getCurrent_player()+ "!!");
			//winnerText.setDisable(false);
		}
		else
		{
			boolean full = game.isFull();
			
			System.out.println(full);
			
			if(full==true)
			{
				//winnerText.setText("Egalité! Dommage");
				//winnerText.setDisable(false);
			}
			else
			{
				game.switchPlayer();
			}
		}
	}
	
	/**
	 * Sets the upper left title
	 * @param title
	 */
	public void setUpperTitle(String title) {
		upperTitle.setText(title);
	}
	
	public void setGame() {
		if(upperTitle.getText()=="Partie contre Homme")
		{
			game = new Game(1);
		}
		else
		{
			game = new Game(2);
		}
		
		System.out.println(game.getCurrent_player());
		
		//turnText.setText("Au tour de " + game.getCurrent_player());
		
		//winnerText.setDisable(true);
	}
}
