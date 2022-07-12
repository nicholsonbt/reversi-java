package application.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartScreen extends Pane {
	public StartScreen() {
		VBox container = new VBox();
		
		Button twoPlayers = new Button("Player Vs. Player");
		Button onePlayer = new Button("Player Vs. AI");
		HBox options = new HBox();
		
		HBox boardSize = new HBox();
		HBox startColour = new HBox();
		
		Label boardSizeLabel = new Label("Board Size:");
		IntegerField rows = new IntegerField();
		Label mult = new Label("X");
		IntegerField cols = new IntegerField();
		
		boardSize.getChildren().add(boardSizeLabel);
		boardSize.getChildren().add(rows);
		boardSize.getChildren().add(mult);
		boardSize.getChildren().add(cols);
		
		options.getChildren().add(boardSize);
		
		container.getChildren().add(twoPlayers);
		container.getChildren().add(onePlayer);
		container.getChildren().add(options);
		
		this.getChildren().add(container);
	}
}
