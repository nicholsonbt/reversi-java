package application;
	
import application.gui.Game;
import application.gui.StartScreen;
import application.logic.Settings;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private static Pane root, start, game;
	private static VBox vbox;
	private static HBox hbox;
	
	private static DoubleProperty ratioProperty = new SimpleDoubleProperty();
	private static DoubleProperty containerWidthProperty = new SimpleDoubleProperty();
	private static DoubleProperty containerHeightProperty = new SimpleDoubleProperty();
	
	@Override public void start(Stage stage) {
		// Initialise root as a new instance of Pane.
		root = new Pane();
		
		vbox = new VBox();
		hbox = new HBox();
		
		ratioProperty.bind(Bindings.createDoubleBinding(() -> {
			return vbox.widthProperty().get() / vbox.heightProperty().get();
		}, vbox.widthProperty(), vbox.heightProperty()));
		
		containerWidthProperty.bind(Bindings.createDoubleBinding(() -> {
			if (Settings.DEFAULT_WIDTH_HEIGHT_RATIO >= ratioProperty.get())
				return vbox.widthProperty().get();
			else
				return vbox.heightProperty().get() * Settings.DEFAULT_WIDTH_HEIGHT_RATIO;
		}, ratioProperty, vbox.widthProperty(), vbox.heightProperty()));
		
		containerHeightProperty.bind(Bindings.createDoubleBinding(() -> {
			if (Settings.DEFAULT_WIDTH_HEIGHT_RATIO <= ratioProperty.get())
				return vbox.heightProperty().get();
			else
				return vbox.widthProperty().get() / Settings.DEFAULT_WIDTH_HEIGHT_RATIO;
		}, ratioProperty, vbox.widthProperty(), vbox.heightProperty()));
		
		// Initialise the settings.
		Settings.Initialise(root);
		
		// Setup the main menu.
		MainMenu();
		
		vbox.prefWidthProperty().bind(stage.widthProperty());
		vbox.prefHeightProperty().bind(stage.heightProperty());
		vbox.setAlignment(Pos.CENTER);
		
		hbox.prefWidthProperty().bind(vbox.widthProperty());
		hbox.prefHeightProperty().bind(containerHeightProperty);
		hbox.setAlignment(Pos.CENTER);
		
		root.prefWidthProperty().bind(containerWidthProperty);
		root.prefHeightProperty().bind(containerHeightProperty);
		
		hbox.getChildren().add(root);
		vbox.getChildren().add(hbox);
		
		// Set the stage with a new scene, containing the root Pane.
		Scene scene = new Scene(vbox, Settings.DEFAULT_WIDTH, Settings.DEFAULT_WIDTH / Settings.DEFAULT_WIDTH_HEIGHT_RATIO);
		stage.setScene(scene);
		
		// Set the default size of the game window.
		
		stage.sizeToScene();
		
        // Set the window title.
        stage.setTitle("Reversi");
        
        // Add the stylesheet to the scene.
        stage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        vbox.getStyleClass().add("root-container");
    	
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
		Settings.SetupGame((Game)game);
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
