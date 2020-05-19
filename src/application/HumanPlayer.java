package application;

import java.io.Serializable;

public class HumanPlayer extends Player implements Serializable {
	
	public HumanPlayer(char _player)
	{
		this.setPlayer(_player);
	}

}
