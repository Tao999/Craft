package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CraftView extends Group {

    private double lX;
    private double lY;
    private double height;
    private double width;

    public CraftView(Scene scene) {

	height = (scene.getHeight() / 3) * 2;
	width = ((scene.getWidth() / 3) * 2) / 2;
	lX = ((scene.getWidth() / 3) * 2 - width) / 2;
	lY = (scene.getHeight() - height) / 2;
	Color bgColor = Color.rgb(198, 198, 198);
//	Color xColor 	= Color.rgb(139, 139, 139);

	Rectangle bg = new Rectangle(width, height);
	bg.setFill(bgColor);
	bg.setArcWidth(25);
	bg.setArcHeight(25);
	bg.setStroke(Color.rgb(85, 85, 85));
	bg.setStrokeWidth(2);

	this.getChildren().add(bg);
	this.setLayoutX(lX);
	this.setLayoutY(lY);

    }

}
