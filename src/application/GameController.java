package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameController {
	private ScreensManager screensmanager = ScreensManager.getInstance();
	
	/**
	 * The game grid
	 */
	@FXML
	private GridPane grid;
	
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
	 * Button to save the current game
	 */
	private Button save;
	
	/**
	 * Button to play again
	 */
	private Button playAgain;
	
	
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
	 * Check if the game is won and if the grid is full
	 * If game won, disables all buttons.
	 * @param event
	 */
	public void placeMark(ActionEvent event) {
		Button r = (Button)event.getSource();
		
		String[][] names = setArray();
		
		int [] index = getIndexes(names, r.getId());		
	
		game.setIntoGrid(index[0], index[1]);
		
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
		
		int [] winningTiles=game.checkWin();
		
		if(game.getIs_over()==true)
		{
			winnerText.setText("Vainqueur: " + game.getCurrent_player()+ "!!");
			winnerText.setVisible(true);
			disableAllButtons(grid);
			gameWon(grid, winningTiles);
		}
		else
		{
			boolean full = game.isFull();
			
			if(full==true)
			{
				winnerText.setText("Egalite! Dommage");
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
	
	
	/**
	 * Set up an instance of a game
	 */
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
	
	/**
	 * Set up the string array containing the names of the buttons.
	 * @return {@code String[][]}
	 */
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
	
	/**
	 * Function that returns the indexes of the panel clicked
	 * @param button
	 * @param name
	 * @return {@code integer[]}
	 */
	public int[] getIndexes(String[][] button, String name)
	{
		int[] index = new int[2];
		
		for(int i=0; i<3 ; i++)
		{
			for(int j=0; j<3; j++)
			{
				if(button[i][j].equals(name))
				{
					index[0]=i;
					index[1]=j;
				}
			}
		}
		
		return index;
	}

	/**
	 * Function that disable all buttons.
	 */
	private void disableAllButtons(GridPane gridPane) {
	    for (Node node : gridPane.getChildren()) 
	    {
	    	node.setDisable(true); 
	    }
	}
	
	public void gameWon(GridPane gridPane, int[] winningTiles) {
	    
		boolean highlighted=false;
		
		int i = 0;
		
		while(highlighted==false) {
			for (Node node : gridPane.getChildren()) {
		        if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 2) {
		        	node.setStyle("-fx-background-color: #00ff00");
		        }
		    }
			
			i=i+2;
		}
		
	}
	
	public void save(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		int saved = 0;
		if(saved == 1) {
			alert.setTitle("Quitter la partie");
			String s = "Voulez-vous vraiment quitter la partie ?";
			alert.setContentText(s);
		}
		else if(saved != 1) {
			alert.setTitle("Quitter la partie");
			String s = "Voulez-vous quitter sans sauvegarder ?";
			alert.setContentText(s);
		}
		 
		Optional<ButtonType> result = alert.showAndWait();
		 
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			// revenir au menu
			System.out.println("ok");
		}
	}
	
	public void playAgain(ActionEvent event) {
		
	}

}

	