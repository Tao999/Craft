package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class AppBase {
	private Inventory inventaire;
	private ArrayList<Item> itemList;
	private Hashtable<Craft, Integer> itemResearch;

	public AppBase() {
		inventaire = new Inventory();
		itemList = new ArrayList<Item>();
		itemResearch = new Hashtable<Craft, Integer>();
		this.initBase();
	}

	private void initBase() {
		int id = Item.NOT_AN_ITEM + 1;
		itemList.add(new Item(id++, "Buche de Bois", ""));//id = 0
		itemList.add(new Item(id++, "Planche de Bois", ""));//id = 1
		itemList.add(new Item(id++, "Batton", ""));
		itemList.add(new Item(id++, "Porte", ""));

		int[][] crafto;
		crafto = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				crafto[i][j] = Item.NOT_AN_ITEM;
		crafto[0][0] = 0;
		this.createCraft(new Craft(crafto), 1);

		crafto = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				crafto[i][j] = Item.NOT_AN_ITEM;
		crafto[0][0] = 1;
		crafto[0][1] = 1;
		this.createCraft(new Craft(crafto), 2);
		
		crafto = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				crafto[i][j] = Item.NOT_AN_ITEM;
		crafto[0][0] = 1;
		crafto[0][1] = 1;
		crafto[0][2] = 1;
		crafto[1][0] = 1;
		crafto[1][1] = 1;
		crafto[1][2] = 1;
		this.createCraft(new Craft(crafto), 3);
		

	}

	public int craftRecipe(Craft craft) {// Transforme un craft en id item
		Object temp = itemResearch.get(craft);
		if (temp == null)
			return Item.NOT_AN_ITEM;
		return (int) temp;
	}

	public boolean createCraft(Craft craft, int idResult) {// créer un craft dans la base de donnée
		boolean verif = false;
		for (int i = 0; i < itemList.size() && !verif; i++)
			if (itemList.get(i).getId() == idResult)
				verif = true;
		if (!verif)
			return false;
		itemResearch.put(craft, idResult);
		return true;
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
