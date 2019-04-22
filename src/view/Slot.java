package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Item;

public class Slot extends Group {

	private ImageView iv;
	private ArrayList<Item> itemList;
	private int imageMargin = 3;
	private double slotSize;
	private double posX;
	private double posY;

	public Slot(int i, int j, int slotMargin, double slotSize, ArrayList<Item> itemList, InnerShadow is) {

		this.posX = i * (slotSize + slotMargin);
		this.posY = j * (slotSize + slotMargin);
		this.itemList = itemList;
		this.slotSize = slotSize;
		Rectangle r = new Rectangle(posX, posY, slotSize, slotSize);

		r.setFill(Color.rgb(50, 50, 50, 0.8));
		r.setArcWidth(5);
		r.setArcHeight(5);
		r.setEffect(is);
		r.setStroke(Color.rgb(198, 198, 198));
		r.setStrokeWidth(2.);
		r.setStrokeType(StrokeType.INSIDE);

		this.getChildren().add(r);
	}

	public void changeItem(int itemId) {
		if (itemId != Item.NOT_AN_ITEM) {
			if (this.getChildren().size() > 1)
				this.getChildren().remove(this.getChildren().size() - 1);
			iv = new ImageView(getItemFromId(itemId).getImage());
			iv.setX(this.posX + imageMargin);
			iv.setY(this.posY + imageMargin);
			iv.setFitHeight(slotSize - 2 * imageMargin);
			iv.setFitWidth(slotSize - 2 * imageMargin);
			this.getChildren().add(iv);
		} else if (this.getChildren().size() > 1) {
			this.getChildren().remove(this.getChildren().size() - 1);
		}
	}

	private Item getItemFromId(int itemId) {
		for (Item item : itemList) {
			if (item.getId() == itemId)
				return item;
		}
		return null;
	}
}
