package application;
	
import application.gui.Game;
import application.gui.StartScreen;
import application.logic.Settings;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static Pane root, start, game;
	
	@Override public void start(Stage stage) {
		// Initialise root as a new instance of Pane.
		root = new Pane();
		
		// Initialise the settings.
		Settings.Initialise(root);
		
		// Setup the main menu.
		MainMenu();
		
		// Set the stage with a new scene, containing the root Pane.
		stage.setScene(new Scene(root));
		
		// Set the default size of the game window.
		stage.setWidth(Settings.DEFAULT_WIDTH);
		stage.setHeight(Settings.DEFAULT_HEIGHT);
		root.prefWidthProperty().bind(stage.widthProperty());
		root.prefHeightProperty().bind(stage.heightProperty());
        
        // Set the window title.
        stage.setTitle("Reversi");
        
        // Add the stylesheet to the scene.
        stage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	
        // Show the stage.
        stage.show();
	}
	
	/**
	 * Removes all children from the root Pane.
	 */
	private static void ClearRoot() {
		root.getChildren().clear();
	}
	
	/**
	 * Clears the root Pane and adds a new instance of Game.
	 */
	public static void PlayGame() {
		ClearRoot();
		game = new Game();
		
		game.prefWidthProperty().bind(root.widthProperty());
		game.prefHeightProperty().bind(root.heightProperty());
		
		root.getChildren().add(game);
		//Settings.SetupGame(game);
	}
	
	/**
	 * Clears the root Pane and adds a new instance of StartScreen.
	 */
	public static void MainMenu() {
		ClearRoot();
		start = new StartScreen();
		
		start.prefWidthProperty().bind(root.widthProperty());
		start.prefHeightProperty().bind(root.heightProperty());
		
		root.getChildren().add(start);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
