package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Item;

public class CraftView extends Group {

	private double lX;
	private double lY;
	private double height;
	private double width;
	private double slotSize;

	private ArrayList<Slot> craftingSlots;
	private ArrayList<Slot> inventorySlots;
	private ArrayList<Item> itemList;
	private Slot recipiesSlot;

	public CraftView(Scene scene, ArrayList<Item> itemList, boolean isWindows) {

		this.itemList = itemList;
		this.craftingSlots = new ArrayList<>();
		this.inventorySlots = new ArrayList<>();
		int offset = (isWindows) ? 10 : 0;

		height = (((scene.getHeight()) + offset) / 3) * 2;
		width = (((scene.getWidth() + offset) / 2) * 2) / 2;
		lX = (((scene.getWidth() + offset) / 3) * 2 - width) / 2;
		lY = ((scene.getHeight() + offset) - height) / 2;
		slotSize = width / 11;

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
		this.getChildren().add(this.drawCraft(0, 0, r.getWidth(), r.getHeight() / 2, slotSize));
		this.getChildren().add(this.drawInventory(0, r.getHeight() / 2, r.getWidth(),
				r.getHeight() / 2 + r.getHeight() / 2, slotSize));

		this.setLayoutX(lX);
		this.setLayoutY(lY);

	}

	private Group drawCraft(double x, double y, double width, double height, double slotSize) {
		int nbSlots = 3;
		int slotMargin = 5;

		double arrowRightUpCornerX = ((x + (width / 4 - nbSlots * (slotSize + slotMargin) / 2))
				+ nbSlots * (slotSize + slotMargin) + (x + ((width / 4) * 3 - slotSize))) / 2 - slotSize;
		double arrowRightUpCornerY = y + (height / 2 - (slotSize + slotMargin) + (slotSize + slotMargin) / 2);
		double arrowSizeX = slotSize * 2;
		double arrowSizeY = slotSize;

		// crafting slots
		Group g1 = new Group();
		Group slotsGroup = new Group();
		InnerShadow is = new InnerShadow();
		is.setOffsetX(3.);
		is.setOffsetY(3.);
		for (int i = 0; i < nbSlots; i++) {
			for (int j = 0; j < nbSlots; j++) {
				Slot slot = new Slot(i, j, slotMargin, slotSize, itemList, is);
				this.craftingSlots.add(slot);
				slotsGroup.getChildren().add(slot);
			}
		}
		slotsGroup.setLayoutX((int) (x + (width / 4 - nbSlots * (slotSize + slotMargin) / 2)));
		slotsGroup.setLayoutY((int) (y + (height / 2 - nbSlots * (slotSize + slotMargin) / 2)));

		// Central Arrow
		Group arrowSlot = new Group();
		arrowSlot.setLayoutX(arrowRightUpCornerX);
		arrowSlot.setLayoutY(arrowRightUpCornerY);
		arrowSlot.getChildren().add(createCraftingArrow(arrowSizeX, arrowSizeY, is));

		// Recipy slot
		recipiesSlot = new Slot(x + ((width / 4) * 3 - slotSize), y + (height / 2 - slotSize), slotSize * 2, itemList,
				is);

		g1.getChildren().addAll(slotsGroup);
		g1.getChildren().add(arrowSlot);
		g1.getChildren().add(recipiesSlot);

		return g1;
	}

	private Polygon createCraftingArrow(double arrowSizeX, double arrowSizeY, InnerShadow is) {
		Polygon p = new Polygon();
		p.getPoints().addAll(
				new Double[] { 0., arrowSizeY / 5 * 2, arrowSizeX / 3 * 2, arrowSizeY / 5 * 2,
					arrowSizeX / 3 * 2, 0., arrowSizeX, arrowSizeY / 2, arrowSizeX / 3 * 2, arrowSizeY,
					arrowSizeX / 3 * 2, arrowSizeY / 5 * 3, 0., arrowSizeY / 5 * 3
				});
		p.setFill(Color.rgb(50, 50, 50, 0.8));
		p.setEffect(is);
		p.setStroke(Color.rgb(198, 198, 198));
		p.setStrokeWidth(1.);
		p.setStrokeType(StrokeType.INSIDE);
		return p;
	}

	private Group drawInventory(double x, double y, double width, double height, double slotSize) {

		int nbSlotsX = 9;
		int nbSlotsY = 3;

		int slotMargin = 5;

		Group g1 = new Group();
		Group slotsGroup = new Group();
		InnerShadow is = new InnerShadow();

		is.setOffsetX(3.);
		is.setOffsetY(3.);

		for (int i = 0; i < nbSlotsX; i++) {
			for (int j = 0; j < nbSlotsY; j++) {
				Slot slot = new Slot(i, j, slotMargin, slotSize, itemList, is);
				this.inventorySlots.add(slot);
				slotsGroup.getChildren().add(slot);
			}
		}

		slotsGroup.setLayoutX((int) (x + (width - nbSlotsX * slotSize) / 4));
		slotsGroup.setLayoutY((int) (y + (height - nbSlotsY * slotSize) / 8));

		g1.getChildren().addAll(slotsGroup);
		return g1;
	}

	public ArrayList<Slot> getInventorySlots() {
		return this.inventorySlots;
	}

	public ArrayList<Slot> getCraftingSlots() {
		return this.craftingSlots;
	}

	public Slot getRecipiesSlot() {
		return this.recipiesSlot;
	}

}
