package application;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreensManager {
	private static ScreensManager single_screensmanager = null;
	
	private HashMap<String, Scene> scenesMap = new HashMap<>();
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
	
	protected void setStageTitle(String title) {
		persistentStage.setTitle(title);
	}
	
	protected void addScene(String name, Scene scene, String css) {
		scene.getStylesheets().add(css);
		scenesMap.put(name, scene);
	}
	
	protected void removeScene(String name) {
		scenesMap.remove(name);
	}
	
	protected void switchToScene(String name) {
		persistentStage.setScene(scenesMap.get(name));
	}
}
