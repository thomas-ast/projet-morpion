package application.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import application.AiPlayer;
import application.Game;
import application.Player;
import application.ScreensManager;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
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
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * Controller for the game view
 *
 */
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
	 * Button to save the current game
	 */
	@FXML
	private Button save;
	
	/**
	 * Button to play again
	 */
	@FXML
	private Button playAgain;
	
	/**
	 * Button to load a game
	 */
	@FXML
	private Button loadGame;
	
	/**
	 * Instance of a game
	 */
	private Game game;
	
	/**
	 * Says if it is saved of not
	 */
	private boolean isSaved;
	
	/**
	 * Brings back to the main menu, checks if saved before
	 * @param event
	 */
	public void backToMenu(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Quitter la partie");
		if(isSaved == true) {
			String s = "Voulez-vous vraiment quitter la partie ?";
			alert.setContentText(s);
		}
		else if(isSaved == false) {
			String s = "Voulez-vous quitter sans sauvegarder ?";
			alert.setContentText(s);
		}
		 
		Optional<ButtonType> result = alert.showAndWait();
		 
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../models/main_menu.fxml"));
				Parent root = (Parent)loader.load();
				screensmanager.switchToScene(new Scene(root));
				screensmanager.setStageTitle("Jeu du morpion - Menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
		if(game.setIntoGrid(index[0], index[1])==true)
		{
			if(game.getCurrentPlayer()=='O')
			{
				r.getStyleClass().add("circle");
				r.applyCss();
				fadeInCase(r);
			}
			else
			{
				r.getStyleClass().add("cross");
				r.applyCss();
				fadeInCase(r);
			}
			
			int [] winningTiles=game.checkWin();
			
			if(game.getIs_over()==true)
			{;
				winnerText.setText("Vainqueur: " + game.getCurrentPlayer()+ "!!");
				winnerText.setVisible(true);
				disableAllButtons(grid);
				gameWon(grid, winningTiles);
				
				loadGame.setDisable(false);
				save.setDisable(true);
			}
			else
			{
				boolean full = game.isFull();
				
				if(full==true)
				{
					winnerText.setText("Egalite! Dommage");
					winnerText.setVisible(true);
					loadGame.setDisable(false);
					save.setDisable(true);
				}
				else
				{
					
					game.switchPlayer();
					turnText.setText("Au tour de " + game.getCurrentPlayer());
					save.setDisable(false);
					
					if(game.getType()==2)
					{
						aiTurn(grid);
						
						winningTiles=game.checkWin();
						
						if(game.getIs_over()==true)
						{
							winnerText.setText("Vainqueur: " + game.getCurrentPlayer()+ "!!");
							winnerText.setVisible(true);
							disableAllButtons(grid);
							gameWon(grid, winningTiles);
							
							loadGame.setDisable(false);
							save.setDisable(true);
						}
						else
						{
							game.switchPlayer();
							turnText.setText("Au tour de " + game.getCurrentPlayer());
						}
					}
				}
			}
		}
	}
	
	/**Case
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
		
		turnText.setText("Au tour de " + game.getCurrentPlayer());
		
		winnerText.setVisible(false);
		
		save.setDisable(true);
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
	 * @param gridPane
	 */
	private void disableAllButtons(GridPane gridPane) {
	    for (Node node : gridPane.getChildren()) 
	    {
	    	node.setDisable(true); 
	    }
	}
	
	/**
	 * Highlights the winning cases
	 * @param gridPane
	 * @param winningTiles
	 */
	public void gameWon(GridPane gridPane, int[] winningTiles) {
		
		int i = 0;
		
		while(i!=6) {
			for (Node node : gridPane.getChildren()) {
		        if (GridPane.getColumnIndex(node) == winningTiles[i+1] && GridPane.getRowIndex(node) == winningTiles[i]) {
		        	node.setStyle("-fx-background-color: #00ff00");
		        }
		    }
			
			i=i+2;
		}
		
	}
	
	/**
	 * Load a game
	 * @param event
	 */
	public void loadGame(ActionEvent event) {
		Game loadedGame = null;
	    try {
	    	FileChooser fc = new FileChooser();
	        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
	        FileChooser.ExtensionFilter fef = new FileChooser.ExtensionFilter("Partie de morpion", "*.ser");
	        fc.getExtensionFilters().add(fef);
	        fc.setSelectedExtensionFilter(fef);
	        File selectedFile = fc.showOpenDialog(screensmanager.getPersistentStage());
	        
	        if(selectedFile != null) {
	        	FileInputStream fileIn = new FileInputStream(selectedFile);
		        ObjectInputStream in = new ObjectInputStream(fileIn);
		        loadedGame = (Game) in.readObject();
		        in.close();
		        fileIn.close();
		        
		        game = loadedGame;
			    Replay(grid);
			    isSaved = false;
	        }

	    } catch (IOException i) {
	        i.printStackTrace();
	        return;
	    } catch (ClassNotFoundException c) {
	        System.out.println("Game class not found");
	        c.printStackTrace();
	        return;
	    }
	}
	
	/**
	 * Save a game, replays the moves
	 * @param event
	 */
	public void save(ActionEvent event) { 
		try {
			FileChooser fc = new FileChooser();
	        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
	        FileChooser.ExtensionFilter fef = new FileChooser.ExtensionFilter("Partie de morpion", "*.ser");
	        fc.getExtensionFilters().add(fef);
	        fc.setSelectedExtensionFilter(fef);
	        fc.setInitialFileName("partie.ser");
	        fc.setTitle("Sauvegarder la partie");
	        File selectedFile = fc.showSaveDialog(screensmanager.getPersistentStage());
	        
	        if(selectedFile != null) {
	        	FileOutputStream fileOut = new FileOutputStream(selectedFile, false);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(game);
				out.close();
				fileOut.close();
				isSaved = true;
	        }
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	/**
	 * Function that reset the grid for another round
	 * @param event
	 */
	public void playAgain(ActionEvent event) {
		game = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../models/game.fxml"));
			Parent root = (Parent)loader.load();
			GameController controller = loader.getController();
			controller.setUpperTitle(upperTitle.getText());
			controller.setGame();
			screensmanager.switchToScene(new Scene(root));
			screensmanager.setStageTitle(screensmanager.getPersistentStage().getTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Animation to fade in the grid
	 */
	public void fadeInCase(Button button) {
		FadeTransition ft = new FadeTransition(Duration.seconds(1), button);
		ft.setFromValue(0.01);
		ft.setToValue(1.5);
		ft.play();
	}
	
	/**
	 * Animation to rotate the grid
	 */
	public void rotateGrid() {
		RotateTransition rt = new RotateTransition(Duration. millis(500), grid);
		rt.setByAngle(360);
		rt.play();
	}
	
	/**
	 * Replays all the moves from the loaded game
	 * @param gridPane
	 */
	public void Replay(GridPane gridPane)
	{
		for(int i = 0 ; i<3 ; i++)
		{
			for(int j = 0 ; j<3 ; j++)
			{
				for (Node node : gridPane.getChildren()) {
			        if (GridPane.getColumnIndex(node) == i  && GridPane.getRowIndex(node) == j ) 
			        {
			        	if(game.getFromGrid(j, i)=='X')
			        	{
			        		node.getStyleClass().add("cross");
							node.applyCss();
			        	}
			        	else if(game.getFromGrid(j, i)=='O')
			        	{
			        		node.getStyleClass().add("circle");
							node.applyCss();
			        	}
			        }
				}
			}
		}
		turnText.setText("Au tour de " + game.getCurrentPlayer());
	}
	
	/**
	 * Function for the turn of the AiPlayer
	 * @param gridPane
	 */
	public void aiTurn(GridPane gridPane)
	{
		Player[] players=game.getPlayers();
		
		AiPlayer ai = (AiPlayer) players[1];
		int [] index=ai.Play(game);
		
		
		for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == index[1]  && GridPane.getRowIndex(node) == index[0] ) 
	        {
	        	if(game.getFromGrid(index[0], index[1])=='X')
	        	{
	        		node.getStyleClass().add("cross");
					node.applyCss();
	        	}
	        	else if(game.getFromGrid(index[0], index[1])=='O')
	        	{
	        		node.getStyleClass().add("circle");
					node.applyCss();
	        	}
	        }
		}
	}

}

	