package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class CraftView extends Group {

    private double lX;
    private double lY;
    private double height;
    private double width;

    public CraftView(Scene scene, boolean isWindows) {

	int offset = (isWindows) ? 10 : 0;

	height = (((scene.getHeight()) + offset) / 3) * 2;
	width = (((scene.getWidth() + offset) / 3) * 2) / 2;
	lX = (((scene.getWidth() + offset) / 3) * 2 - width) / 2;
	lY = ((scene.getHeight() + offset) - height) / 2;

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

	this.getChildren().add(r);
	this.getChildren().add(this.drawCraft(0, 0, r.getWidth(), r.getHeight() / 2));
	this.getChildren()
		.add(this.drawInventory(0, r.getHeight() / 2, r.getWidth(), r.getHeight() / 2 + r.getHeight() / 2));

	this.setLayoutX(lX);
	this.setLayoutY(lY);

    }

    private Group drawCraft(double x, double y, double width, double height) {

	double xOffset = 10;
	double yOffset = 10;

	int slotSize = 50;
	int slotMargin = 5;

	Group g1 = new Group();
	Group slotsGroup = new Group();
	InnerShadow is = new InnerShadow();

	is.setOffsetX(3.);
	is.setOffsetY(3.);

	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		Rectangle r = new Rectangle(i * (slotSize + slotMargin), j * (slotSize + slotMargin), slotSize,
			slotSize);
		r.setFill(Color.rgb(50, 50, 50, 0.8));
		r.setArcWidth(5);
		r.setArcHeight(5);
		r.setEffect(is);
		r.setStroke(Color.rgb(198, 198, 198));
		r.setStrokeWidth(2.);
		r.setStrokeType(StrokeType.INSIDE);
		slotsGroup.getChildren().add(r);
	    }
	}

	slotsGroup.setLayoutX(xOffset);
	slotsGroup.setLayoutY(yOffset);

	g1.getChildren().addAll(slotsGroup);
	return g1;
    }

    private Group drawInventory(double x, double y, double width, double height) {
	Group g1 = new Group();

	return g1;
    }

}
