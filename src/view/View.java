package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import model.Item;

public class View {

	private static String OS = System.getProperty("os.name").toLowerCase();
	private Color bgColor = Color.rgb(127, 172, 255);
	private ArrayList<Item> itemList = null;

	public View(ArrayList<Item> itemList) {
		super();
		this.itemList = itemList;
	}

	public Scene getScene() {

		Group root = new Group();
		Scene scene = new Scene(root, 854, 480, bgColor);

		root.requestFocus();

		InventoryBarView inventoryBar = new InventoryBarView(scene, isWindows());
		ItemListView itemListView = new ItemListView(scene, isWindows(), this.itemList);
		ResearchBarView researchBar = new ResearchBarView(scene, isWindows(), itemListView);
		CraftView craftView = new CraftView(scene, isWindows());

		root.getChildren().add(inventoryBar);
		root.getChildren().add(itemListView);
		root.getChildren().add(researchBar);
		root.getChildren().add(craftView);

		scene.getStylesheets().add(getClass().getClassLoader().getResource("Style.css").toExternalForm());

		return scene;
	}

	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

}
