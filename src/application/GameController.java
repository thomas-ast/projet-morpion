package application;

import java.io.IOException;

import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
	private Button case00;
	
	/**
	 * The upper mid case
	 */
	@FXML
	private Button case01;
	
	/**
	 * The upper right case
	 */
	@FXML
	private Button case02;
	
	/**
	 * The middle left case
	 */
	@FXML
	private Button case10;
	
	/**
	 * The middle mid case
	 */
	@FXML
	private Button case11;
	
	/**
	 * The middle right case
	 */
	@FXML
	private Button case12;
	
	/**
	 * The bottom left case
	 */
	@FXML
	private Button case20;
	
	/**
	 * The bottom mid case
	 */
	@FXML
	private Button case21;
	
	/**
	 * The bottom right case
	 */
	@FXML
	private Button case22;
	
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
		
		System.out.println(r.getId());
		
		String[][] button = setArray();
		
		int [] index = getIndexes(button, r.getId());
		
		//System.out.println(index[0]+" et "+ index[1]);
		
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
			winnerText.setText("Vainqueur: " + game.getCurrent_player()+ "!!");
			winnerText.setVisible(true);
		}
		else
		{
			boolean full = game.isFull();
			
			if(full==true)
			{
				winnerText.setText("Egalit�! Dommage");
				winnerText.setVisible(true);
			}
			else
			{
				game.switchPlayer();
				turnText.setText("Au tour de " + game.getCurrent_player());
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
		
		turnText.setText("Au tour de " + game.getCurrent_player());
		
		winnerText.setVisible(false);
	}
	
	public String[][] setArray() {
		
		String[][] button = new String[3][3];
		
		for(int i=0; i<3 ; i++)
		{
			for(int j=0; j<3; j++)
			{
				button[i][j]="case"+i+j;
			}
		}
		
		return button;
	}
	
	public int[] getIndexes(String[][] button, String name)
	{
		int[] index = new int[2];
		
		for(int i=0; i<3 ; i++)
		{
			for(int j=0; j<3; j++)
			{
				if(button[i][j]==name)
				{
					index[0]=i;
					index[1]=j;
				}
			}
		}
		
		return index;
	}

}

	