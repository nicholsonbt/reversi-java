package application.gui.game;

import application.logic.Settings;
import application.logic.SquareColour;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Square extends Button {
	private DoubleProperty absoluteWidthProperty, absoluteHeightProperty;
	public int row, col;
	
	public Square(int row, int col) {
		this.row = row;
		this.col = col;
		
		this.absoluteWidthProperty = new SimpleDoubleProperty();
		this.absoluteHeightProperty = new SimpleDoubleProperty();
		
		minWidthProperty().bind(absoluteWidthProperty);
		prefWidthProperty().bind(absoluteWidthProperty);
		maxWidthProperty().bind(absoluteWidthProperty);
		
		minHeightProperty().bind(absoluteHeightProperty);
		prefHeightProperty().bind(absoluteHeightProperty);
		maxHeightProperty().bind(absoluteHeightProperty);
		
		this.disableProperty().bind(Bindings.createBooleanBinding(() -> {
			return !Settings.checkTwoPlayer() && !Settings.boardEnabledProperty.get();
		}, Settings.boardEnabledProperty));
		
		this.getStyleClass().add("reversiSquare");
	}
	
	public void SetColour(SquareColour colour, double opacity) {
		Image img;
		
		if (colour == SquareColour.BLACK)
			img = new Image("/black.png");
		else 
			img = new Image("/white.png");
		
		ImageView view = new ImageView(img);
		
		view.fitHeightProperty().bind(heightProperty());
		view.setPreserveRatio(true);
		view.setOpacity(opacity);
		
		this.setGraphic(view);
	}
	
	public void Refresh() {
		if (getGraphic() != null && getGraphic().getOpacity() != 1)
			setGraphic(null);
	}
	
	public DoubleProperty absoluteWidthProperty() {
		return absoluteWidthProperty;
	}
	
	public DoubleProperty absoluteHeightProperty() {
		return absoluteHeightProperty;
	}
}
