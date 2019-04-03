package view;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class ItemListView extends Group {

    private double lX;
    private double width;

    public ItemListView(Scene scene, boolean isWindows) {

	int offset = (isWindows) ? 10 : 0;
	width = (scene.getWidth() + offset) / 3;
	lX = width * 2;

	Rectangle r = new Rectangle(width, scene.getHeight() + offset);
	InnerShadow is = new InnerShadow();

	is.setOffsetX(5.);
	is.setOffsetY(5.);

	r.setFill(Color.rgb(50, 50, 50, 0.8));
	r.setEffect(is);

	this.getChildren().add(r);
	this.getChildren()
		.add(createList(width, scene.getHeight(), new ArrayList<Integer>(Collections.nCopies(100, 0))));
	this.setLayoutX(lX);

    }

    private Group createList(double width, double height, ArrayList</* Items */Integer> itemList) {
	Group gp = new Group();
	ScrollPane sp = new ScrollPane();

	int nbItems = itemList.size();
	int xOffset = 15;
	int yOffset = 15;
	int slotSize = 40;
	int slotMargin = 5;
	int nbSlotsLine = (int) (width / (slotSize + slotMargin));
	int nbSlotsRow = nbItems / nbSlotsLine + 1;

	Group slotsGroup = new Group();
	for (int i = 0; i < nbSlotsLine; i++) {
	    for (int j = 0; j < nbSlotsRow; j++) {
		Rectangle r = new Rectangle(i * (slotSize + slotMargin), j * (slotSize + slotMargin), slotSize,
			slotSize);
		r.setFill(Color.rgb(50, 50, 50, 0.8));
		r.setArcWidth(5);
		r.setArcHeight(5);
		r.setStroke(Color.rgb(198, 198, 198));
		r.setStrokeWidth(2.);
		r.setStrokeType(StrokeType.INSIDE);
		slotsGroup.getChildren().add(r);
	    }
	}

	sp.setContent(slotsGroup);
	sp.setPannable(true);
	sp.setPrefSize(width, height - 15);

	gp.getChildren().add(sp);
	gp.setLayoutX(xOffset);
	gp.setLayoutY(yOffset);
	return gp;
    }

}
