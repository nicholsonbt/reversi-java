package application.gui;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class IntegerField extends TextField {
	public IntegerField() {
		this.getStyleClass().addAll("integer-field");

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
		
		System.out.println(this.getChildren());
	}
	
	public IntegerField(int defaultValue) {
		this();
		
		this.setPromptText(String.valueOf(defaultValue));
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
