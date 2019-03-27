package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class AppBase {
	private Inventory inventaire;
	private ArrayList<Item> itemList;
	private Hashtable<Integer, Integer> itemResearch;

	public AppBase() {
		inventaire = new Inventory();
		itemList = new ArrayList<Item>();
		itemResearch = new Hashtable<Integer, Integer>();
		this.initBase();
	}

	private void initBase() {
		int id = Item.NOT_AN_ITEM + 1;
		itemList.add(new Item(id++, "premiere item", ""));

		int[][] craft = new int[3][3];
		craft[0][0] = 1;
		craft[0][1] = 1;
		craft[0][2] = Item.NOT_AN_ITEM;
		craft[1][0] = Item.NOT_AN_ITEM;
		craft[1][1] = Item.NOT_AN_ITEM;
		craft[1][2] = Item.NOT_AN_ITEM;
		craft[2][0] = Item.NOT_AN_ITEM;
		craft[2][1] = Item.NOT_AN_ITEM;
		craft[2][2] = Item.NOT_AN_ITEM;
		itemList.add(new Item(id++, "un deuxieme item", "", craft));

		for (int i = 0; i < itemList.size(); i++) {
			Item temp;
			temp = itemList.get(i);
			if (temp.getRecipe() != null)
				itemResearch.put(temp.hashCode(), temp.getId());
		}
	}

	public int getItemIdFromRecipe(int code) {
		if (!itemResearch.containsKey(code))
			return Item.NOT_AN_ITEM;
		return itemResearch.get(code);
	}

	public int getItemIdFromRecipe(int[][] recipe) {// A FINIIIR
		if (recipe[0][0] == Item.NOT_AN_ITEM && recipe[0][1] == Item.NOT_AN_ITEM && recipe[0][2] == Item.NOT_AN_ITEM) {
			recipe[0][0] = recipe[1][0];
			recipe[0][1] = recipe[1][1];
			recipe[0][2] = recipe[1][2];
			recipe[3][0] = Item.NOT_AN_ITEM;
			recipe[3][1] = Item.NOT_AN_ITEM;
			recipe[3][2] = Item.NOT_AN_ITEM;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(recipe[i][j] + " ");
			}
			System.out.println();
		}

		return getItemIdFromRecipe(getCodeFromRecipe(recipe));
	}

	public int getCodeFromRecipe(int[][] recipe) {// transforme une recette en code
		if (recipe == null)
			return 0;
		int code = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				code += recipe[i][j] * (60 + i + j);
			}
		}
		return code;
	}

	public Item getItemById(int id) {// retourne l'item par rapport à un id
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getId() == id)
				return itemList.get(i);
		}
		return null;
	}

	public Item getInventory(int index) {// retourne l'item de l'inventaire
		return inventaire.getInventory(index);
	}

	public boolean putItem(Item item, int index) {// met un item dans une place
		return inventaire.pushItem(item);
	}

	public boolean pushItem(Item item) {// met un item dans la premiere case libre
		return inventaire.pushItem(item);
	}

	public void eraseInventory() {// efface l'inventaire
		inventaire.eraseInventory();
	}

	public int pickId(int index) {// retourne l'id de l'item choisi, et
		// l'enleve de l'inventaire
		return inventaire.pickId(index);
	}

	public int getId(int index) {// retoure l'id de l'item choisi
		return inventaire.getId(index);
	}

}
