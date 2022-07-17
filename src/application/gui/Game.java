package application.gui;

import application.gui.game.Board;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Game extends Pane {
	
	public Board board;
	public Transcript transcript;
	
	public Game() {
		HBox content = new HBox();
		
		board = new Board();
		transcript = new Transcript();
		
		transcript.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return widthProperty().get() * (1 / 5.0);
		}, widthProperty()));
		
		transcript.prefHeightProperty().bind(heightProperty());
		
		board.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return widthProperty().get() * (3 / 5.0);
		}, widthProperty()));
		
		board.setAlignment(Pos.CENTER);
		
		board.prefHeightProperty().bind(heightProperty());
		
		content.getChildren().add(transcript);
		content.getChildren().add(board);
		
		this.getChildren().add(content);
	}
}
