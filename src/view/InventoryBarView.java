package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Item;

public class InventoryBarView extends Group {

	public class IBSlot extends Group {

		private ImageView iv;
		private ArrayList<Item> itemList;
		private int itemID;
		private int posX;
		private int imageMargin = 3;
		private int slotSize = 40;

		public IBSlot(int i, int itemId, ArrayList<Item> itemList) {

			this.itemList = itemList;
			this.posX = i * slotSize;

			InnerShadow is = new InnerShadow();
			Rectangle r = new Rectangle(this.posX, 0, slotSize, slotSize);

			is.setOffsetX(3.);
			is.setOffsetY(3.);

			r.setFill(Color.rgb(50, 50, 50, 0.8));
			r.setEffect(is);
			r.setStroke(Color.rgb(198, 198, 198));
			r.setStrokeWidth(2.);
			r.setStrokeType(StrokeType.INSIDE);

			this.getChildren().add(r);
		}

		public void changeItem(int itemId) {
			this.itemID = itemId;
			if (itemId != Item.NOT_AN_ITEM) {
				if (this.getChildren().size() > 1)
					this.getChildren().remove(this.getChildren().size() - 1);
				iv = new ImageView(getItemFromId(itemId).getImage());
				iv.setX(this.posX + imageMargin);
				iv.setY(0 + imageMargin);
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

		public int getItemId() {
			return this.itemID;
		}

	}

	private int nbSlots;
	private double lX;
	private double lY;
	private ArrayList<IBSlot> slots;

	public InventoryBarView(Scene scene, boolean isWindows, ArrayList<Item> itemList) {

		try {
			this.nbSlots = 9;
			int offset = (isWindows) ? 10 : 0;
			this.lX = (((scene.getWidth() + offset) / 3) * 2 - this.nbSlots * 40) / 2;
			this.lY = scene.getHeight() + offset - 40;
			this.slots = new ArrayList<InventoryBarView.IBSlot>();
			Group slotsGroup = new Group();

			for (int i = 0; i < this.nbSlots; i++)
				this.slots.add(new IBSlot(i, Item.NOT_AN_ITEM, itemList));
			slotsGroup.getChildren().addAll(this.slots);

			this.getChildren().addAll(slotsGroup);
			this.setLayoutX(this.lX);
			this.setLayoutY(this.lY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<IBSlot> getIBSlots() {
		return this.slots;
	}

}
