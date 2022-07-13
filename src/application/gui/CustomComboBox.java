package application.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class CustomComboBox<T> extends ComboBox<T> {
	private final static String DEFAULT_BACKGROUND_COLOUR = "e6e9eb";
	private final static String DEFAULT_HIGHLIGHT_BACKGROUND_COLOUR = "59acff";
	private final static String DEFAULT_TEXT_COLOUR = "000000";
	private final static String DEFAULT_HIGHLIGHT_TEXT_COLOUR = "ffffff";
	
	public ObjectProperty<Background> backgroundProperty = new SimpleObjectProperty<Background>();
	public ObjectProperty<Background> backgroundHighlightProperty = new SimpleObjectProperty<Background>();
	public ObjectProperty<Paint> textFillProperty = new SimpleObjectProperty<Paint>();
	public ObjectProperty<Paint> textFillHighlightProperty = new SimpleObjectProperty<Paint>();
	public ObjectProperty<T> selectedProperty = new SimpleObjectProperty<T>();
	public ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>();

	public CustomComboBox() {
		InitialiseDefaults();
		BindCells();
		BindSelected();
	}
	
	private void InitialiseDefaults() {
		backgroundProperty.set(Background.fill(Paint.valueOf(DEFAULT_BACKGROUND_COLOUR)));
		backgroundHighlightProperty.set(Background.fill(Paint.valueOf(DEFAULT_HIGHLIGHT_BACKGROUND_COLOUR)));
		textFillProperty.set(Paint.valueOf(DEFAULT_TEXT_COLOUR));
		textFillHighlightProperty.set(Paint.valueOf(DEFAULT_HIGHLIGHT_TEXT_COLOUR));
		fontProperty.set(new Font(14));
	}
	
	private void BindCells() {
		this.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override public ListCell<T> call(ListView<T> param) {
				
				final ListCell<T> cell = new ListCell<T>() {

					@Override public void updateItem(T item, boolean empty) {
						super.updateItem(item, empty);
						
						if (item != null) {
							
							setText((String)item); 
							
							backgroundProperty().bind(Bindings.createObjectBinding(() -> {
								if (hoverProperty().get())
									return backgroundHighlightProperty.get();

								return backgroundProperty.get();
							}, hoverProperty(), backgroundProperty, backgroundHighlightProperty));
							
							textFillProperty().bind(Bindings.createObjectBinding(() -> {
								if (hoverProperty().get())
									return textFillHighlightProperty.get();

								return textFillProperty.get();
							}, hoverProperty(), textFillProperty, textFillHighlightProperty));
							
							fontProperty().bind(fontProperty);
						}
						
						else {
							setText(null);
						}
					}
				};
				
				return cell;
			}
		});
	}
	
	private void BindSelected() {
		this.setButtonCell(new ListCell<T>(){
		    @Override
		    protected void updateItem(T item, boolean btl){
		        super.updateItem(item, btl);
		        if(item != null) {
		            textProperty().bind(Bindings.createStringBinding(() -> {
						return (String)valueProperty().get();
					}, valueProperty()));
		            
		            backgroundProperty().bind(Bindings.createObjectBinding(() -> {
						return backgroundProperty.get();
					}, backgroundProperty));
					
					textFillProperty().bind(Bindings.createObjectBinding(() -> {
						return textFillProperty.get();
					}, textFillProperty));
					
					fontProperty().bind(fontProperty);
		        }
		    }
		});
	}
	
	public void SetBackgroundProperty(Background background) {
		backgroundProperty.set(background);
	}
	
	public void SetBackgroundHighlightProperty(Background background) {
		backgroundHighlightProperty.set(background);
	}
	
	public void SetTextFillProperty(Paint colour) {
		textFillProperty.set(colour);
	}
	
	public void SetTextFillHighlightProperty(Paint colour) {
		textFillHighlightProperty.set(colour);
	}
	
	public Background GetBackgroundProperty() {
		return backgroundProperty.get();
	}
	
	public Background GetBackgroundHighlightProperty() {
		return backgroundHighlightProperty.get();
	}
	
	public Paint GetTextFillProperty() {
		return textFillProperty.get();
	}
	
	public Paint GetTextFillHighlightProperty() {
		return textFillHighlightProperty.get();
	}
}