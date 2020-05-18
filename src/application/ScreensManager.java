package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreensManager {
	private static ScreensManager single_screensmanager = null;
	private Stage persistentStage;
	
	private ScreensManager() {
		
	}
	
	public static ScreensManager getInstance() {
		if(single_screensmanager == null) {
			single_screensmanager = new ScreensManager();
		}
		return single_screensmanager;
	}
	
	public void init(Stage stage, Scene scene) {
		this.persistentStage = stage;
		
		stage.setScene(scene);
		stage.setTitle("Jeu du morpion - Menu");
		
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.show();
	}
	
	public Stage getPersistentStage() {
		return persistentStage;
	}
	
	protected void setStageTitle(String title) {
		persistentStage.setTitle(title);
	}
	
	protected void switchToScene(Scene scene) {
		persistentStage.setScene(scene);
	}
}
