package view;

import java.io.FileInputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class InventoryBarView extends Group {

	public InventoryBarView(Scene scene) {

		try {
			int nbSlots = 9;
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

			this.setLayoutX((scene.getWidth() - nbSlots * 40) / 2);
			this.setLayoutY(scene.getHeight() - 40);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
