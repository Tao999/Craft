package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.ItemModel;

public class Slot extends Group {

	private int itemID;
	private ImageView iv;
	private ArrayList<ItemModel> itemList;
	private int imageMargin = 3;
	private double slotSize;
	private double posX;
	private double posY;
	private int nbX;
	private int nbY;

	public Slot(double x, double y, double slotsize, ArrayList<ItemModel> itemList, InnerShadow is) {
		super();
		this.posX = x;
		this.posY = y;
		this.slotSize = slotsize;
		this.itemList = itemList;

		Rectangle r = new Rectangle(x, y, slotsize, slotSize);
		r.setFill(Color.rgb(50, 50, 50, 0.8));
		r.setArcWidth(5);
		r.setArcHeight(5);
		r.setEffect(is);
		r.setStroke(Color.rgb(198, 198, 198));
		r.setStrokeWidth(2.);
		r.setStrokeType(StrokeType.INSIDE);

		this.getChildren().add(r);
	}

	public Slot(int i, int j, int slotMargin, double slotSize, ArrayList<ItemModel> itemList, InnerShadow is) {
		super();
		this.nbX = i;
		this.nbY = j;
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
		this.itemID = itemId;
		if (itemId != ItemModel.NOT_AN_ITEM) {
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

	public int getItemId() {
		return this.itemID;
	}

	private ItemModel getItemFromId(int itemId) {
		for (ItemModel item : itemList) {
			if (item.getId() == itemId)
				return item;
		}
		return null;
	}

	public int[] getIndex() {
		return new int[] { this.nbX, this.nbY };
	}
}
