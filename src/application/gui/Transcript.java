package application.gui;

import application.logic.SquareColour;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Transcript extends Pane {
	
	private VBox content;
	private ScrollPane scroll;
	
	private boolean autoScroll;
	
	public Transcript() {
		VBox container = new VBox();
		scroll = new ScrollPane();
		
		content = new VBox();
		Label title = new Label("Transcipt:");
		
		title.getStyleClass().add("label");
		
		this.getStyleClass().add("transcript");
		
		title.prefWidthProperty().bind(this.widthProperty());
		title.setAlignment(Pos.CENTER);
		
		content.prefWidthProperty().bind(this.widthProperty());
		content.setAlignment(Pos.CENTER);
		
		DoubleBinding customBinding = Bindings.createDoubleBinding(() -> {
			double totalHeight = this.heightProperty().doubleValue();
			double titleHeight = title.heightProperty().doubleValue();
			return totalHeight - titleHeight;
			
		}, this.heightProperty(), title.heightProperty());
		
		scroll.prefHeightProperty().bind(customBinding);
		scroll.setContent(content);
		scroll.setFitToWidth(true);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		scroll.vvalueProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (autoScroll) {
					scroll.setVvalue(scroll.getVmax());
					autoScroll = false;
				}
			}
		});
		
		container.getChildren().add(title);
		container.getChildren().add(scroll);
		
		this.getChildren().add(container);
	}
	
	private void AddLine(String line) {
		if (scroll.getVvalue() == scroll.getVmax())
			autoScroll = true;
		
		content.getChildren().add(new Text(line));
	}
	
	public void AddMove(SquareColour player, int row, int col) {
		String line = "";
		
		if (player == SquareColour.BLACK)
			line += "B: (";
		else
			line += "W: (";
		
		line += row + ", " + col + ")";
		
		
		AddLine(line);
	}
}
