package view;

import java.io.FileInputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class InventoryBar extends Group {

    public InventoryBar(Scene scene, int nbSlots) {

	try {
	    Image slotsImg = new Image(new FileInputStream("E:\\Images\\images_projet_java\\inventory_slot.png"));

	    StackPane imageContainer = new StackPane();
	    Group slotsGroup = new Group();

	    for (int i = 0; i < nbSlots; i++) {
		ImageView invSlot = new ImageView(slotsImg);
		invSlot.setX(i * 40);
		slotsGroup.getChildren().add(invSlot);
	    }

	    imageContainer.getChildren().addAll(slotsGroup);
	    this.getChildren().add(imageContainer);

	    this.setLayoutX((scene.getHeight() - imageContainer.getWidth()) / 2);
	    this.setLayoutY(scene.getHeight() - 40);

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
