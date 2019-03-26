package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CraftView extends Group {

    private double lX;
    private double lY;
    private double height;
    private double width;

    public CraftView(Scene scene) {

	height = (((scene.getHeight()) + 10) / 3) * 2;
	width = (((scene.getWidth() + 10) / 3) * 2) / 2;
	lX = (((scene.getWidth() + 10) / 3) * 2 - width) / 2;
	lY = ((scene.getHeight() + 10) - height) / 2;

	Rectangle r = new Rectangle(width, height);
	InnerShadow is = new InnerShadow();

	is.setOffsetX(5.);
	is.setOffsetY(5.);
	is.setColor(Color.rgb(100, 100, 100));

	r.setFill(Color.rgb(198, 198, 198));
	r.setArcWidth(25);
	r.setArcHeight(25);
	r.setStroke(Color.rgb(85, 85, 85));
	r.setStrokeWidth(2);
	r.setEffect(is);

	this.drawCraft(0, 0, r.getWidth(), r.getHeight() / 2);
	this.drawInventory(0, r.getHeight() / 2, r.getWidth(), r.getHeight() / 2 + r.getHeight() / 2);

	this.getChildren().add(r);
	this.setLayoutX(lX);
	this.setLayoutY(lY);

    }

    private void drawCraft(double x, double y, double width, double height) {
	
	
	
    }
    
    private void drawInventory(double x, double y, double width, double height) {
    }
    

}
