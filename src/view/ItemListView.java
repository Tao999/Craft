package view;

import java.util.ArrayList;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.ItemModel;

public class ItemListView extends Group {

	private double lX;
	private double width;
	private Scene scene;
	private ArrayList<ItemModel> itemList;
	private ArrayList<ItemListSlot> slots;

	public ItemListView(Scene scene, boolean isWindows, ArrayList<ItemModel> itemList) {

		this.itemList = itemList;
		this.scene = scene;

		int offset = (isWindows) ? 10 : 0;
		width = (scene.getWidth() + offset) / 3;
		lX = width * 2;

		Rectangle r = new Rectangle(width, scene.getHeight() + offset);
		InnerShadow is = new InnerShadow();

		is.setOffsetX(5.);
		is.setOffsetY(5.);

		r.setFill(Color.rgb(50, 50, 50, 0.8));
		r.setEffect(is);

		this.setOnMouseDragged(null);
		this.getChildren().add(r);
		this.getChildren().add(createList(width, scene.getHeight(), itemList));
		this.setLayoutX(lX);
	}

	private Group createList(double width, double height, ArrayList<ItemModel> itemList) {
		Group gp = new Group();
		ScrollPane sp = new ScrollPane();
		TilePane tp = new TilePane();

		this.slots = new ArrayList<ItemListSlot>();

		int xOffset = 15;
		int yOffset = 15;
		int slotSize = 40;
		int slotMargin = 10;
		int imageMargin = 3;
		int nbRows = (int) (width / (slotSize + slotMargin));

		tp.setOrientation(Orientation.HORIZONTAL);
		tp.setTileAlignment(Pos.TOP_LEFT);
		tp.setPrefRows(nbRows);
		tp.setHgap(slotMargin);
		tp.setVgap(slotMargin);

		for (ItemModel item : itemList) {
			ItemListSlot slot = new ItemListSlot(item, slotSize, imageMargin);
			slots.add(slot);
			tp.getChildren().add(slot);
		}

		sp.setContent(tp);
		sp.setPannable(true);
		sp.setPrefSize(width, height - 15);

		gp.getChildren().add(sp);
		gp.setLayoutX(xOffset);
		gp.setLayoutY(yOffset);

		return gp;
	}

	public void searchItem(String str) {
		ArrayList<ItemModel> tmp = new ArrayList<>();

		// Le .contains sur des chaines dans une arraylist est certes tres couteux, mais
		// on a pour l'instant une petite base d'objets et c'est rapide a faire, mais
		// dans l'ideal il faudrait que changer cette methode de recherche
		for (ItemModel item : this.itemList)
			if (item.getName().toLowerCase().contains(str.toLowerCase())
					|| item.getDescription().toLowerCase().contains(str.toLowerCase()))
				tmp.add(item);

		this.getChildren().set(1, createList(width, this.scene.getHeight(), tmp));
	}

	public ArrayList<ItemListSlot> getItems() {
		return this.slots;
	}

}
