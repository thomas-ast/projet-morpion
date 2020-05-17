package application;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScreensManager {
	private static ScreensManager single_scenesmanager = null;
	
	private HashMap<String, Pane> scenesMap = new HashMap<>();
	private Scene persistentScene;
	private Stage persistentStage;
	
	private ScreensManager() {
		
	}
	
	public static ScreensManager getInstance() {
		if(single_scenesmanager == null) {
			single_scenesmanager = new ScreensManager();
		}
		return single_scenesmanager;
	}
	
	public void init(Stage stage) {
		VBox root = null;
		try {
			root = (VBox)FXMLLoader.load(getClass().getResource("views/main_menu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		this.persistentStage = stage;
		
		this.persistentScene = new Scene(root,1280,720);
		persistentScene.getStylesheets().add(getClass().getResource("views/main_menu.css").toExternalForm());
		
		stage.setScene(persistentScene);
		stage.setTitle("Jeu du morpion - Menu");
		
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.show();
	}
	
	protected void setStageTitle(String title) {
		persistentStage.setTitle(title);
	}
	
	protected void addScene(String name, Pane pane, String css) {
		scenesMap.put(name, pane);
	}
	
	protected void removeScene(String name) {
		scenesMap.remove(name);
	}
	
	protected void switchToScene(String name) {
		persistentScene.setRoot(scenesMap.get(name));
	}
}
