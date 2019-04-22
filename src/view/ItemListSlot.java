package view;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Item;

public class ItemListSlot extends Group {

	private Item item;

	public ItemListSlot(Item item, int slotSize, int imageMargin) {

		this.item = item;

		Rectangle r = new Rectangle(slotSize, slotSize);
		ImageView iv = new ImageView(item.getImage());

		Tooltip.install(this, new Tooltip(item.getDescription()));

		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				r.setFill(Color.rgb(200, 200, 200, 0.8));
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				r.setFill(Color.rgb(50, 50, 50, 0.8));
			}
		});

		r.setFill(Color.rgb(50, 50, 50, 0.8));
		r.setArcWidth(5);
		r.setArcHeight(5);
		r.setStroke(Color.rgb(198, 198, 198));
		r.setStrokeWidth(2.);
		r.setStrokeType(StrokeType.INSIDE);

		iv.setX(imageMargin);
		iv.setY(imageMargin);
		iv.setFitHeight(slotSize - 2 * imageMargin);
		iv.setFitWidth(slotSize - 2 * imageMargin);

		this.getChildren().add(r);
		this.getChildren().add(iv);
	}

	public Item getItem() {
		return this.item;
	}

}
