package application.gui.game;

import application.logic.SquareColour;
import application.logic.Settings;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {
	public Board() {
		Square square;
		DoubleBinding lengthBinding = Bindings.createDoubleBinding(() -> {
			double w = prefWidthProperty().get() / Settings.GetCols();
			double h = prefHeightProperty().get() / Settings.GetRows();
			return Math.min(w, h);
		}, prefWidthProperty(), prefHeightProperty());
		
		for (int row = 0; row < Settings.GetRows(); row++) {
			for (int col = 0; col < Settings.GetCols(); col++) {
				square = new Square(row, col);
				
				square.absoluteWidthProperty().bind(lengthBinding);
				square.absoluteHeightProperty().bind(lengthBinding);
				
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
	
	public void ColourSquare(int row, int col, SquareColour colour, double opacity) {
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
