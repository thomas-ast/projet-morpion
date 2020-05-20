package application;

import java.io.Serializable;

/**
 * Class representing a Human Player
 * @author Lucas DAMIENS
 *
 */
public class HumanPlayer extends Player implements Serializable {
	
	/**
	 * Constructor of a Human Player
	 * @param _player
	 */
	public HumanPlayer(char _player)
	{
		this.setPlayer(_player);
	}

}
