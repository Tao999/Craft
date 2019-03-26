package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ItemListView extends Group {

    private double lX;
    private double width;

    public ItemListView(Scene scene) {

	width = (scene.getWidth() + 10) / 3;
	lX = width * 2;

	Rectangle r = new Rectangle(width, scene.getHeight() + 10);
	InnerShadow is = new InnerShadow();

	is.setOffsetX(5.);
	is.setOffsetY(5.);

	r.setFill(Color.rgb(50, 50, 50, 0.8));
	r.setEffect(is);

	this.getChildren().add(r);
	this.setLayoutX(lX);

    }

}
