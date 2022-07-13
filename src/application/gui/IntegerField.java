package application.gui;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

public class IntegerField extends TextField {
	private final static String DEFAULT_BACKGROUND_COLOUR = "e6e9eb";
	private final static String DEFAULT_HIGHLIGHT_BACKGROUND_COLOUR = "59acff";
	private final static String DEFAULT_TEXT_COLOUR = "000000";
	private final static String DEFAULT_HIGHLIGHT_TEXT_COLOUR = "ffffff";
	
	public ObjectProperty<Background> backgroundProperty = new SimpleObjectProperty<Background>();
	public ObjectProperty<Paint> backgroundHighlightProperty = new SimpleObjectProperty<Paint>();
	public ObjectProperty<Paint> textFillProperty = new SimpleObjectProperty<Paint>();
	public ObjectProperty<Paint> textFillHighlightProperty = new SimpleObjectProperty<Paint>();
	
	
	
	public IntegerField() {
		InitialiseDefaults();
		
		this.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldString, String newString) {
				StringProperty textProperty = (StringProperty) observable;
				IntegerField integerField = (IntegerField) textProperty.getBean();

				Platform.runLater(() -> {
					integerField.TextChanged();
				});
			}
		});
		
		this.backgroundProperty().bind(backgroundProperty);
		
		this.setSkin(new TextFieldSkin(this){
            @Override
            protected void layoutChildren(double x, double y, double w, double h) {
                super.layoutChildren(x, y, w, h);
                this.textFillProperty().bind(textFillProperty);
                this.highlightFillProperty().bind(backgroundHighlightProperty);
                this.highlightTextFillProperty().bind(textFillHighlightProperty);
            }
        });
		
	}
	
	public IntegerField(int defaultValue) {
		this();
		
		this.setPromptText(String.valueOf(defaultValue));
	}
	
	private void InitialiseDefaults() {
		backgroundProperty.set(Background.fill(Paint.valueOf(DEFAULT_BACKGROUND_COLOUR)));
		backgroundHighlightProperty.set(Paint.valueOf(DEFAULT_HIGHLIGHT_BACKGROUND_COLOUR));
		textFillProperty.set(Paint.valueOf(DEFAULT_TEXT_COLOUR));
		textFillHighlightProperty.set(Paint.valueOf(DEFAULT_HIGHLIGHT_TEXT_COLOUR));
	}
	
	private void TextChanged() {
		String newString = this.getText();
		
		if (!newString.matches("\\d*")) {
			this.setText(newString.replaceAll("[\\D]", ""));
		}
		
		else if (newString.matches("0\\d*")) {
			this.deleteText(0, 1);
		}
	}
}
