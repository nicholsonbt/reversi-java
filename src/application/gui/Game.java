package application.gui;

import application.gui.game.Board;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Game extends Pane {
	
	public Board board;
	public Transcript transcript;
	
	public Game() {
		HBox content = new HBox();
		
		board = new Board();
		transcript = new Transcript();
		
		transcript.setPrefWidth(100);
		
		transcript.prefHeightProperty().bind(content.heightProperty());
		
		content.getChildren().add(transcript);
		content.getChildren().add(board);
		
		this.getChildren().add(content);
	}
}
