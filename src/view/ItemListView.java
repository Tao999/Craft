package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ItemListView extends Group {

    private double lX;
    private double width;

    public ItemListView(Scene scene) {

	width = scene.getWidth() / 3;
	lX = width * 2;

	Rectangle bg = new Rectangle(width, scene.getHeight());
	bg.setFill(Color.rgb(50, 50, 50, 0.8));

	this.getChildren().add(bg);
	this.setLayoutX(lX);

    }

}
