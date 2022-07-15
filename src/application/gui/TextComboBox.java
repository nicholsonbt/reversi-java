package application.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class TextComboBox extends ComboBox<String> {
	private DoubleProperty heightProperty;
	private ObjectProperty<Font> fontProperty;
	
	public TextComboBox() {
		heightProperty = new SimpleDoubleProperty();
		fontProperty = new SimpleObjectProperty<Font>();
		
		fontProperty.set(new Font(0));
		
		heightProperty.bind(heightProperty());
		
		this.setButtonCell(new ListCell<String>(){
		    @Override
		    protected void updateItem(String item, boolean btl){
		        super.updateItem(item, btl);
		        if(item != null) {
		            textProperty().bind(Bindings.createStringBinding(() -> {
						return (String)valueProperty().get();
					}, valueProperty()));

					fontProperty().bind(fontProperty);
		        }
		    }
		});
		
		this.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override public ListCell<String> call(ListView<String> param) {
				
				final ListCell<String> cell = new ListCell<String>() {
					@Override public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						
						prefHeightProperty().bind(heightProperty);
						fontProperty().bind(fontProperty);
						setText(item); 
					}
				};
				
				return cell;
			}
		});
	}
	
	public ObjectProperty<Font> fontProperty() {
		return fontProperty;
	}
	
	public Font getFont() {
		return fontProperty().get();
	}
	
	public void setFont(Font font) {
		fontProperty().set(font);
	}
}
