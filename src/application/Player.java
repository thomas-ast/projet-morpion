package application;

/**
 * Abstract class representing a Player
 * @author Lucas DAMIENS
 *
 */
public abstract class Player {

	/**
	 * Char of the player: X or O
	 */
	private char player;
	
	/**
	 * Getter for the players char
	 * @return {@code char}
	 */
	public char getPlayer() {
		return player;
	}

	/**
	 * Setter for the players char
	 * @param player
	 */
	public void setPlayer(char player) {
		this.player = player;
	}
	
}
