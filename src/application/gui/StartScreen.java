package application.gui;

import application.logic.Settings;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartScreen extends Pane {
	private VBox container;
	private HBox subContainer0, subContainer1, subContainer2, subContainer3, boardSizeInput;
	private FlowPane boardSizeContainer, startColourContainer;
	private Button twoPlayers, onePlayer, toggleBackground;
	private Label boardSizeLabel, mult, startColourLabel;
	private IntegerField rows, cols;
	private TextComboBox choice;
	
	public StartScreen() {
		InitialiseWidgets();
		SetupCustomEvents();
		SetupWidgets();
		BindWidgets();
		AddWidgets();
	}
	
	private void InitialiseWidgets() {
		// VBox
		container = new VBox();
		
		// HBoxes
		subContainer0 = new HBox();
		subContainer1 = new HBox();
		subContainer2 = new HBox();
		subContainer3 = new HBox();
		boardSizeInput = new HBox();
		
		// FlowPanes
		boardSizeContainer = new FlowPane();
		startColourContainer = new FlowPane();
		
		// Buttons
		twoPlayers = new Button("Player Vs. Player");
		onePlayer = new Button("Player Vs. AI");
		toggleBackground = new Button();
		
		// Labels
		boardSizeLabel = new Label("Board Size:");
		mult = new Label("x");
		startColourLabel = new Label("Start Colour:");
		
		// IntegerFields
		rows = new IntegerField(8);
		cols = new IntegerField(8);
		
		// ComboBoxes
		choice = new TextComboBox();
	}
	
	
	private void SetupCustomEvents() {
		toggleBackground.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Settings.ToggleLightDark();
            }
        });
	}
	
	private void SetupWidgets() {
		// Still need to bind font.
		twoPlayers.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 30.0));
		}, container.widthProperty()));
		
		onePlayer.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 30.0));
		}, container.widthProperty()));
		
		toggleBackground.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 60.0));
		}, container.widthProperty()));
		
		boardSizeLabel.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 80.0));
		}, container.widthProperty()));
		
		rows.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 80.0));
		}, container.widthProperty()));
		
		mult.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 80.0));
		}, container.widthProperty()));
		
		cols.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 80.0));
		}, container.widthProperty()));
		
		startColourLabel.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 80.0));
		}, container.widthProperty()));
		
		choice.fontProperty().bind(Bindings.createObjectBinding(() -> {
			return new Font(container.widthProperty().get() * (1 / 80.0));
		}, container.widthProperty()));

		startColourLabel.paddingProperty().bind(Bindings.createObjectBinding(() -> {
			return new Insets(0, container.widthProperty().get() * (1 / 108.0), 0, 0);
		}, container.widthProperty()));
		
		
		
		subContainer0.setAlignment(Pos.TOP_RIGHT);
		subContainer1.setAlignment(Pos.CENTER);
		subContainer2.setAlignment(Pos.CENTER);
		subContainer3.setAlignment(Pos.CENTER);
		boardSizeContainer.setAlignment(Pos.CENTER);
		startColourContainer.setAlignment(Pos.CENTER);
		
		choice.getItems().addAll("Black", "White");
		choice.getSelectionModel().selectFirst();
	}
	
	
	private void BindWidgets() {
		toggleBackground.textProperty().bind(Bindings.createStringBinding(() -> {
			if (Settings.lightModeProperty.get())
				return "Dark Mode";
			
			return "Light Mode";
		}, Settings.lightModeProperty));
		
		
		toggleBackground.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.widthProperty().get() * (1 / 8.0);
		}, container.widthProperty()));
		
		toggleBackground.prefHeightProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.heightProperty().get() * (1 / 12.0);
		}, container.heightProperty()));
		
		
		
		twoPlayers.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.widthProperty().get() * (1 / 2.0);
		}, container.widthProperty()));
		
		twoPlayers.prefHeightProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.heightProperty().get() * (1 / 6.0);
		}, container.heightProperty()));
		
		
		
		onePlayer.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.widthProperty().get() * (1 / 2.0);
		}, container.widthProperty()));
		
		onePlayer.prefHeightProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.heightProperty().get() * (1 / 6.0);
		}, container.heightProperty()));
		
		
		choice.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.widthProperty().get() * (1 / 12.0);
		}, container.widthProperty()));
		
		rows.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.widthProperty().get() * (1 / 20.0);
		}, container.widthProperty()));
		
		
		cols.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
			return container.widthProperty().get() * (1 / 20.0);
		}, container.widthProperty()));

		
		
		subContainer1.paddingProperty().bind(Bindings.createObjectBinding(() -> {
			double h = container.heightProperty().get();
			return new Insets(h * (5 / 36.0), 0, h * (1 / 24.0), 0);
		}, container.heightProperty()));
		
		subContainer2.paddingProperty().bind(Bindings.createObjectBinding(() -> {
			double h = container.heightProperty().get();
			return new Insets(h * (1 / 24.0), 0, h * (5 / 36.0), 0);
		}, container.heightProperty()));
		
		
		container.prefWidthProperty().bind(this.widthProperty());
		container.prefHeightProperty().bind(this.heightProperty());
		
		subContainer0.prefWidthProperty().bind(container.widthProperty());
		subContainer1.prefWidthProperty().bind(container.widthProperty());
		subContainer2.prefWidthProperty().bind(container.widthProperty());
		subContainer3.prefWidthProperty().bind(container.widthProperty());
	}
	
	private void AddWidgets() {
		// This should prevent the input widgets from being separated.
		boardSizeInput.getChildren().addAll(rows, mult, cols);
		
		boardSizeContainer.getChildren().addAll(boardSizeLabel, boardSizeInput);
		startColourContainer.getChildren().addAll(startColourLabel, choice);
		
		
		subContainer0.getChildren().add(toggleBackground);
		subContainer1.getChildren().add(twoPlayers);
		subContainer2.getChildren().add(onePlayer);
		subContainer3.getChildren().addAll(boardSizeContainer, startColourContainer);
		
		container.getChildren().addAll(subContainer0, subContainer1, subContainer2, subContainer3);
		this.getChildren().add(container);
	}
}
