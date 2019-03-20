package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class ResearchBarView extends Group {

	public ResearchBarView(Scene scene) {

		TextField textField = new TextField();
		textField.setLayoutX(3);
		textField.setLayoutY(3);
		textField.setPrefWidth(400);
		textField.setPromptText("Search");

		this.setLayoutX((scene.getWidth() - textField.getPrefWidth()) / 2);
		this.setLayoutY(10);

		this.getChildren().add(textField);
	}

}
