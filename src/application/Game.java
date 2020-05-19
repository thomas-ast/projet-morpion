package application;
import java.io.Serializable;
import java.util.Random;

/**
 * This class represent the game.
 * 
 * @author Lucas DAMIENS
 *
 */
public class Game implements Serializable {
	
	/**
	 * Array representing the tic tac toe game.
	 */
	private char grid[][];
	
	/**
	 * Boolean used to check if game is over.
	 */
	private boolean is_over;
	
	/**
	 * Char used to know which players turn it is.
	 * 'X' or 'O'
	 */
	private char current_player;
	
	/**
	 * Array containing the players: Humon or Ai
	 */
	private Player[] players = new Player[2];
	
	/**
	 * Int containing the type of the game:
	 * - 1 = Human vs Human
	 * - 2 = Human vs Ai
	 */
	private int type;
	
	
	/**
	 * Constructor
	 */
	public Game(int _type) {
		this.grid= new char[3][3];
		this.setIs_over(false);
		this.setType(_type);
		this.setCurrentPlayer();
		this.setPlayers();
		
	}
	
	/**
	 * Function used to check if one of the player as won.
	 * And returns the indexes of the winning tiles
	 * @return {@code integer[]} 
	 */
	public int[] checkWin() {
		
		int[] winningTiles = new int[6];
		
		for(int i = 0; i<3; i++)
		{
			if(grid[i][0]==current_player && grid[i][1]==current_player && grid[i][2]==current_player)
			{
				setIs_over(true);
				
				winningTiles[0]=i;
				winningTiles[1]=0;
				winningTiles[2]=i;
				winningTiles[3]=1;
				winningTiles[4]=i;
				winningTiles[5]=2;
			}
		}
		
		for(int i = 0; i<3; i++)
		{
			if(grid[0][i]==current_player && grid[1][i]==current_player && grid[2][i]==current_player)
			{
				setIs_over(true);
				
				winningTiles[0]=0;
				winningTiles[1]=i;
				winningTiles[2]=1;
				winningTiles[3]=i;
				winningTiles[4]=2;
				winningTiles[5]=i;
			}
		}
		
		if(grid[0][0]==current_player && grid[1][1]==current_player && grid[2][2]==current_player)
		{
			setIs_over(true);
			
			winningTiles[0]=0;
			winningTiles[1]=0;
			winningTiles[2]=1;
			winningTiles[3]=1;
			winningTiles[4]=2;
			winningTiles[5]=2;
		}
		
		if(grid[0][2]==current_player && grid[1][1]==current_player && grid[2][0]==current_player)
		{
			setIs_over(true);
			
			winningTiles[0]=0;
			winningTiles[1]=2;
			winningTiles[2]=1;
			winningTiles[3]=1;
			winningTiles[4]=2;
			winningTiles[5]=0;
		}
		
		return winningTiles;
	}

	/**
	 * getter for the boolean is_over
	 * @return {@code boolean}
	 */
	public boolean getIs_over() {
		return is_over;
	}

	/**
	 * setter for the boolean is_over
	 * @param is_over
	 */
	public void setIs_over(boolean is_over) {
		this.is_over = is_over;
	}
	
	public void setGrid()
	{
		this.grid = new char[3][3];
		
		for(int i=0; i<3; i++)
		{
			for(int j =0; j<3; j++)
			{
				this.grid[i][j]=' ';
			}
		}
		
	}
	
	/**
	 * Setter for a value in grid[x][y]
	 * @param x
	 * @param y
	 */
	public boolean setIntoGrid(int x, int y)
	{
		if(this.grid[x][y]!='X' && this.grid[x][y]!='O')
		{
			this.grid[x][y]=this.current_player;
			return true;
		}
		else
			return false;
	}
	
	
	/**
	 * Used for the beginning of the game.
	 * Random number choosing which player 
	 * will play first.
	 */
	public void setCurrentPlayer()
	{
		Random randomNum = new Random();
		int choice = randomNum.nextInt(2);
		
		if(choice==0)
		{
			current_player='X';
		}
		else
		{
			current_player='O';
		}
		
	}
	
	/**
	 * Getter for the current player char
	 * @return {@code char}
	 */
	public char getCurrentPlayer()
	{
		return this.current_player;
	}
	
	/**
	 * Function used to switch turns.
	 */
	public void switchPlayer() {
		
		if(current_player=='X')
			current_player='O';
		else
			current_player='X';
			
	}
	
	/**
	 * Function used to reset the game.
	 */
	public void reset() {
		for(int i=0; i<3; i++)
		{
			for(int j =0; j<3; j++)
			{
				this.grid[i][j]=' ';
			}
		}
	}

	/**
	 * Function used to check if the grid is full.
	 * @return {@code boolean}
	 */
	public boolean isFull() {
		boolean full=true;
		
		for(int i=0; i<3; i++)
		{
			for(int j =0; j<3; j++)
			{
				if(this.grid[i][j]=='X' && full==true || this.grid[i][j]=='O' && full==true)
				{
					full=true;
				}
				else
				{
					full=false;
				}
			}
		}
		
		return full;
	}

	/**
	 * Getter for the type of the game
	 * @return {@code int}
	 */
	public int getType() {
		return type;
	}

	/**
	 * Setter for the type of the game
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Getter for the player array
	 * @return
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Setter for the player array.
	 * Depending on the type of the game.
	 */
	public void setPlayers() {
		
		this.players[0]= new HumanPlayer(this.getCurrentPlayer());
				
		if(this.getType()==1)
		{
			if(this.getCurrentPlayer()=='X')
				this.players[1]= new HumanPlayer('O');
			else
				this.players[1]= new HumanPlayer('X');
		}
		else
		{
			if(this.getCurrentPlayer()=='X')
				this.players[1]= new AiPlayer('O');
			else
				this.players[1]= new AiPlayer('X');
		}
	}
}


