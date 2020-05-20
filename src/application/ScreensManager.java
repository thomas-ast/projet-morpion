package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Manager for the different views
 *
 */
public class ScreensManager {
	/**
	 * Singleton instance
	 */
	private static ScreensManager single_screensmanager = null;
	
	/**
	 * Persistent stage that changes scenes to change screen
	 */
	private Stage persistentStage;
	
	/**
	 * Private constructor for getInstance to create one
	 */
	private ScreensManager() {
		
	}
	
	/**
	 * Returns an screensmanager instance
	 * @return
	 */
	public static ScreensManager getInstance() {
		if(single_screensmanager == null) {
			single_screensmanager = new ScreensManager();
		}
		return single_screensmanager;
	}
	
	/**
	 * Initiliaze the stage with several defined parameters
	 * @param stage
	 * @param scene
	 */
	public void init(Stage stage, Scene scene) {
		this.persistentStage = stage;
		
		stage.setScene(scene);
		stage.setTitle("Jeu du morpion - Menu");
		
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.show();
	}
	
	/**
	 * Returns the persistentStage instance
	 * @return
	 */
	public Stage getPersistentStage() {
		return persistentStage;
	}
	
	/**
	 * Sets the window title
	 * @param title
	 */
	public void setStageTitle(String title) {
		persistentStage.setTitle(title);
	}
	
	/**
	 * Put the scene on the screen
	 * @param scene
	 */
	public void switchToScene(Scene scene) {
		persistentStage.setScene(scene);
	}
}
