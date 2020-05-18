package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		
		Game game = new Game(1);
		
		game.setIntoGrid(0, 0);
		
		game.setIntoGrid(1, 1);
		
		game.setIntoGrid(2, 2);
		
		game.checkWin();
		
		if(game.getIs_over()==true)
			System.out.print(game.getCurrent_player() + " a gagné");
	}
}
