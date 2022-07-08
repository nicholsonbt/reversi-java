package application;
	
import application.gui.Board;
import application.logic.Settings;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Settings.Initialise();
		
		Board board = new Board();
		
		// Set the stage with a new scene, containing a new instance of GUI (extends Pane).
		stage.setScene(new Scene(board));
		
		stage.setWidth(500);
		stage.setHeight(500);
        
        // Set the window title.
        stage.setTitle("Reversi");
    	
        // Show the stage.
        stage.show();
        
        stage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        Settings.Setup(board);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
