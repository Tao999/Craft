package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import model.ItemModel;

public class View {

	private static String OS = System.getProperty("os.name").toLowerCase();
	private Color bgColor = Color.rgb(127, 172, 255);
	private ArrayList<ItemModel> itemList = null;

	public InventoryBarView inventoryBarView;
	public ItemListView itemListView;
	public ResearchBarView researchBarView;
	public CraftView craftView;

	public View(ArrayList<ItemModel> itemList) {
		super();
		this.itemList = itemList;
	}

	public Scene getScene() {

		Group root = new Group();
		Scene scene = new Scene(root, 854, 480, bgColor);

		root.requestFocus();

		this.inventoryBarView = new InventoryBarView(scene, isWindows(), this.itemList);
		this.itemListView = new ItemListView(scene, isWindows(), this.itemList);
		this.researchBarView = new ResearchBarView(scene, isWindows(), itemListView);
		this.craftView = new CraftView(scene, itemList, isWindows());

		root.getChildren().add(inventoryBarView);
		root.getChildren().add(itemListView);
		root.getChildren().add(researchBarView);
		root.getChildren().add(craftView);

		scene.getStylesheets().add(getClass().getClassLoader().getResource("Style.css").toExternalForm());

		return scene;
	}

	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

}
