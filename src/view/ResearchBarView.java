package view;

import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class ResearchBarView extends Group {

	private double lX;
	private double lY;
	private TextField textField;

	public ResearchBarView(Scene scene, boolean isWindows, ItemListView ilv) {
		int offset = (isWindows) ? 10 : 0;
		textField = new TextField();
		textField.setLayoutX(3);
		textField.setLayoutY(3);
		textField.setPrefWidth(400);
		textField.setPromptText("Search");

		lX = (((scene.getWidth() + offset) / 3) * 2 - textField.getPrefWidth()) / 2;
		lY = 10;

		this.setLayoutX(lX);
		this.setLayoutY(lY);

		this.getChildren().add(textField);
	}

	public StringProperty getTextFieldText() {
		return textField.textProperty();
	}

}
