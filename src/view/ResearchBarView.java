package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class ResearchBarView extends Group {

    private double lX;
    private double lY;

    public ResearchBarView(Scene scene) {

	TextField textField = new TextField();
	textField.setLayoutX(3);
	textField.setLayoutY(3);
	textField.setPrefWidth(400);
	textField.setPromptText("Search");

	lX = ((scene.getWidth() / 3) * 2 - textField.getPrefWidth()) / 2;
	lY = 10;

	this.setLayoutX(lX);
	this.setLayoutY(lY);

	this.getChildren().add(textField);
    }

}
