package application;

import java.io.Serializable;
import java.util.Random;

/**
 * This class represent the Ai Player
 * @author Lucas DAMIENS
 *
 */
public class AiPlayer extends Player implements Serializable {

	/**
	 * Constructor of the class
	 * @param _player
	 */
	public AiPlayer(char _player)
	{
		this.setPlayer(_player);
	}
	
	/**
	 * Function that play a turn of the game putting his char
	 * in an empty case and returns its indexes
	 * @param game
	 * @return {@code integer[]}
	 */
	public int[] Play(Game game)
	{
		int[] index = new int[2]; 
		
		Random randomNum = new Random();
		
		boolean played=false;
		while(played==false)
		{
			int x = randomNum.nextInt(3);
			int y = randomNum.nextInt(3);
		
			if(game.getFromGrid(x, y)!='X' && game.getFromGrid(x, y)!='O')
			{
				game.setIntoGrid(x, y);
				played=true;
				index[0]=x;
				index[1]=y;
			}
		}
		
		return index;
	}
	
}
