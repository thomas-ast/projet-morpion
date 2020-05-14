package application;
import java.util.Random;

public class Game {
	
	private char grid[][];
	private boolean is_over;
	private int type;
	private char current_player;
	
	public Game(int _type) {
		this.grid= new char[3][3];
		this.setIs_over(false);
		this.type=_type;
		
	}
	
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

	public boolean getIs_over() {
		return is_over;
	}

	public void setIs_over(boolean is_over) {
		this.is_over = is_over;
	}
	
	public void setCurrent_player()
	{
		Random randomNum = new Random();
		int choice = randomNum.nextInt(2);
		
		if(choice==0)
			current_player='X';
		else
			current_player='O';
		
	}
	
	public void switchPlayer() {
		
		if(current_player=='X')
			current_player='O';
		else
			current_player='X';
			
	}
		
}


