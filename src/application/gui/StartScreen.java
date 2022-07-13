package application.gui;

import application.logic.Settings;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class StartScreen extends Pane {
	private VBox container;
	private HBox options, boardSize, startColour;
	private Button twoPlayers, onePlayer, toggleBackground;
	private Label boardSizeLabel, mult, startColourLabel;
	private IntegerField rows, cols;
	private CustomComboBox<String> choice;
	private StringBinding textBinding;
	private EventHandler<ActionEvent> toggleEvent;
	private EventHandler<MouseEvent> mouseEnterEvent, mouseExitEvent;
	
	public StartScreen() {
		InitialiseWidgets();
		SetupCustomBindings();
		SetupCustomEvents();
		SetupWidgets();
		SetupStyles();
		BindWidgets();
		AddWidgets();
	}
	
	private void InitialiseWidgets() {
		// VBox
		container = new VBox();
		
		// HBoxes
		options = new HBox();
		boardSize = new HBox();
		startColour = new HBox();
		
		// Buttons
		twoPlayers = new Button("Player Vs. Player");
		onePlayer = new Button("Player Vs. AI");
		toggleBackground = new Button();
		
		// Labels
		boardSizeLabel = new Label("Board Size:");
		mult = new Label("X");
		startColourLabel = new Label("Start Colour:");
		
		// IntegerFields
		rows = new IntegerField(8);
		cols = new IntegerField(8);
		
		// ComboBoxes
		choice = new CustomComboBox<String>();
	}
	
	private void SetupCustomBindings() {
		textBinding = Bindings.createStringBinding(() -> {
			if (Settings.lightModeProperty.get())
				return "Dark Mode";
			
			return "Light Mode";
		}, Settings.lightModeProperty);
	}
	
	private void SetupCustomEvents() {
		toggleEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Settings.ToggleLightDark();
            }
        };
        
	}
	
	private void SetupWidgets() {
		toggleBackground.textProperty().bind(textBinding);
		toggleBackground.setOnAction(toggleEvent);
		
		rows.setPrefSize(30, 20);
		cols.setPrefSize(30, 20);
		
		choice.getItems().addAll("Black", "White");
		choice.getSelectionModel().selectFirst();
	}
	
	private void SetupStyles() {
		twoPlayers.getStyleClass().add("customBorder");
		onePlayer.getStyleClass().add("customBorder");
		rows.getStyleClass().add("customBorder");
		cols.getStyleClass().add("customBorder");
		choice.getStyleClass().add("customBorder");
		toggleBackground.getStyleClass().add("customBorder");
	}
	
	
	private void BindWidgets() {
		this.backgroundProperty().bind(Settings.backgroundColourBinding);
		
		choice.backgroundProperty.bind(Settings.backgroundColourBinding);
		choice.backgroundHighlightProperty.bind(Settings.highlightBackgroundColourBinding);
		choice.textFillProperty.bind(Settings.textColourBinding);
		choice.textFillHighlightProperty.bind(Settings.highlightTextColourBinding);
		choice.fontProperty.bind(Settings.GetFontBinding(14));
		
		rows.backgroundProperty.bind(Settings.backgroundColourBinding);
		rows.backgroundHighlightProperty.bind(Settings.highlightColourBinding);
		rows.textFillProperty.bind(Settings.textColourBinding);
		rows.textFillHighlightProperty.bind(Settings.highlightTextColourBinding);
		rows.fontProperty().bind(Settings.GetFontBinding(14));
		
		cols.backgroundProperty.bind(Settings.backgroundColourBinding);
		cols.backgroundHighlightProperty.bind(Settings.highlightColourBinding);
		cols.textFillProperty.bind(Settings.textColourBinding);
		cols.textFillHighlightProperty.bind(Settings.highlightTextColourBinding);
		cols.fontProperty().bind(Settings.GetFontBinding(14));

		twoPlayers.textFillProperty().bind(Settings.textColourBinding);
		twoPlayers.fontProperty().bind(Settings.GetFontBinding(20));
		
		onePlayer.textFillProperty().bind(Settings.textColourBinding);
		onePlayer.fontProperty().bind(Settings.GetFontBinding(20));
		
		toggleBackground.textFillProperty().bind(Settings.textColourBinding);
		toggleBackground.fontProperty().bind(Settings.GetFontBinding(14));
		
		boardSizeLabel.textFillProperty().bind(Settings.textColourBinding);
		boardSizeLabel.fontProperty().bind(Settings.GetFontBinding(14));
		
		mult.textFillProperty().bind(Settings.textColourBinding);
		mult.fontProperty().bind(Settings.GetFontBinding(14));
		
		startColourLabel.textFillProperty().bind(Settings.textColourBinding);
		startColourLabel.fontProperty().bind(Settings.GetFontBinding(14));
	}
	
	private void AddWidgets() {
		boardSize.getChildren().addAll(boardSizeLabel, rows, mult, cols);
		startColour.getChildren().addAll(startColourLabel, choice);
		options.getChildren().addAll(boardSize, startColour, toggleBackground);
		container.getChildren().addAll(twoPlayers, onePlayer, options);
		this.getChildren().add(container);
	}
}
