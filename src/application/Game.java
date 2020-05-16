package application;
import java.util.Random;

/**
 * This class represent the game.
 * 
 * @author Lucas DAMIENS
 *
 */
public class Game {
	
	/**
	 * Array representing the tic tac toe game.
	 */
	private char grid[][];
	
	/**
	 * Boolean used to check if game is over.
	 */
	private boolean is_over;
	
	/**
	 * Int used to know which types of game it is.
	 * 1=player vs player
	 * 2=player vs computer
	 */
	private int type;
	
	/**
	 * Char used to know which players turn it is.
	 * 'X' or 'O'
	 */
	private char current_player;
	
	/**
	 * Constructor
	 */
	public Game(int _type) {
		this.grid= new char[3][3];
		this.setIs_over(false);
		this.type=_type;
		
	}
	
	/**
	 * Function used to check if one of the player as won.
	 */
	public void checkWin() {
		for(int i = 0; i<3; i++)
		{
			if(grid[i][0]==current_player && grid[i][1]==current_player && grid[i][2]==current_player)
			{
				setIs_over(true);
			}
		}
		
		for(int i = 0; i<3; i++)
		{
			if(grid[0][i]==current_player && grid[1][i]==current_player && grid[2][i]==current_player)
			{
				setIs_over(true);
			}
		}
		
		if(grid[0][0]==current_player && grid[1][1]==current_player && grid[2][2]==current_player)
		{
			setIs_over(true);
		}
		
		if(grid[0][2]==current_player && grid[1][1]==current_player && grid[2][0]==current_player)
		{
			setIs_over(true);
		}
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
	
	
	/**
	 * Used for the beginning of the game.
	 * Random number choosing which player 
	 * will play first.
	 */
	public void setCurrent_player()
	{
		Random randomNum = new Random();
		int choice = randomNum.nextInt(2);
		
		if(choice==0)
			current_player='X';
		else
			current_player='O';
		
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
		boolean full=false;
		
		for(int i=0; i<3; i++)
		{
			for(int j =0; j<3; j++)
			{
				if(this.grid[i][j]=='X' || this.grid[i][j]=='O' || full!=true)
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
}


