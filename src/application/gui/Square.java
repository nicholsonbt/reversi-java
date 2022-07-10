package application.gui;

import application.logic.Colour;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Square extends Button {
	public int row, col;
	
	public Square(int row, int col) {
		this.row = row;
		this.col = col;
		
		this.getStyleClass().add("reversiSquare");
	}
	
	public void SetColour(Colour colour, double opacity) {
		Image img;
		
		if (colour == Colour.BLACK)
			img = new Image("/black.png");
		else 
			img = new Image("/white.png");
		
		ImageView view = new ImageView(img);
		
		view.fitHeightProperty().bind(this.heightProperty());
		view.setPreserveRatio(true);
		view.setOpacity(opacity);
		
		this.setGraphic(view);
	}
	
	public void Refresh() {
		if (this.getGraphic() != null && this.getGraphic().getOpacity() != 1)
			this.setGraphic(null);
	}
}
