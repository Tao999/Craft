package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class InventoryBarView extends Group {

	private int nbSlots;
	private double lX;
	private double lY;

	public InventoryBarView(Scene scene) {

		try {
			nbSlots = 9;
			lX = (((scene.getWidth() + 10) / 3) * 2 - nbSlots * 40) / 2;
			lY = scene.getHeight() + 10 - 40;
			Group slotsGroup = new Group();

			InnerShadow is = new InnerShadow();
			is.setOffsetX(3.);
			is.setOffsetY(3.);

			for (int i = 0; i < nbSlots; i++) {
				Rectangle r = new Rectangle(i * 40, 0, 40, 40);
				r.setFill(Color.rgb(50, 50, 50, 0.8));
				r.setEffect(is);
				r.setStroke(Color.rgb(198, 198, 198));
				r.setStrokeWidth(2.);
				r.setStrokeType(StrokeType.INSIDE);
				slotsGroup.getChildren().add(r);
			}

			this.getChildren().addAll(slotsGroup);
			this.setLayoutX(lX);
			this.setLayoutY(lY);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
