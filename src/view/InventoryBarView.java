package view;

import java.io.FileInputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class InventoryBarView extends Group {

    private int nbSlots;
    private double lX;
    private double lY;

    public InventoryBarView(Scene scene) {

	try {
	    nbSlots = 9;
	    lX = ((scene.getWidth() / 3) * 2 - nbSlots * 40) / 2;
	    lY = scene.getHeight() - 40;
	    Image slotsImg = new Image(new FileInputStream("resources/images/inventory_slot.png"));

	    StackPane imageContainer = new StackPane();
	    Group slotsGroup = new Group();

	    for (int i = 0; i < nbSlots; i++) {
		ImageView invSlot = new ImageView(slotsImg);
		invSlot.setX(i * 40);
		slotsGroup.getChildren().add(invSlot);
	    }

	    imageContainer.getChildren().addAll(slotsGroup);

	    this.getChildren().add(imageContainer);
	    this.setLayoutX(lX);
	    this.setLayoutY(lY);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
