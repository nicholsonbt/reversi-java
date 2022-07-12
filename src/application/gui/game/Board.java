package application.gui.game;

import application.logic.Colour;
import application.logic.Settings;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {
	public Board() {
		Square square;
		
		for (int row = 0; row < Settings.GetRows(); row++) {
			for (int col = 0; col < Settings.GetCols(); col++) {
				square = new Square(row, col);
				
				square.setMinHeight(Settings.GetSquareLength());
				square.setPrefHeight(Settings.GetSquareLength());
				square.setMaxHeight(Settings.GetSquareLength());
				
				square.minWidthProperty().bind(square.heightProperty());
				square.prefWidthProperty().bind(square.heightProperty());
				square.maxWidthProperty().bind(square.heightProperty());
				
				square.setOnAction(e -> {
					// Get clicked square.
					Square source = (Square)e.getSource();

					// Try placing the piece.
					Settings.PlacePiece(source.row, source.col);
				});
				
				this.add(square, col, row);
			}
		}
	}
	
	public void ColourSquare(int row, int col, Colour colour, double opacity) {
		GetSquare(row, col).SetColour(colour, opacity);
	}
	
	public void RefreshBoard() {
		for (Node node : this.getChildren()) {
	        ((Square)node).Refresh();
	    }
	}
	
	private Square GetSquare(int row, int col) {
		Node square = null;

	    for (Node node : this.getChildren()) {
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
	            square = node;
	            break;
	        }
	    }

	    return (Square)square;
	}
}
