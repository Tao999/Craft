package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Model {

	public InventoryModel inventaire;
	private ArrayList<ItemModel> itemList;
	private Hashtable<CraftModel, Integer> itemResearch;

	public Model() {
		this.inventaire = new InventoryModel();
		this.itemList = new ArrayList<ItemModel>();
		this.itemResearch = new Hashtable<CraftModel, Integer>();
	}

	public Model(ArrayList<ItemModel> itemList, Hashtable<CraftModel, Integer> itemResearch) {
		this.inventaire = new InventoryModel();
		this.itemList = itemList;
		this.itemResearch = itemResearch;
	}

	public int craftRecipe(CraftModel craft) {// Transforme un craft en id item
		Object temp = itemResearch.get(craft);
		if (temp == null)
			return ItemModel.NOT_AN_ITEM;
		return (int) temp;
	}

	public boolean createCraft(CraftModel craft, int idResult) {// creer un craft dans la base de donnée
		boolean verif = false;
		for (int i = 0; i < itemList.size() && !verif; i++)
			if (itemList.get(i).getId() == idResult)
				verif = true;
		if (!verif)
			return false;
		itemResearch.put(craft, idResult);
		return true;
	}

	public ItemModel getItemById(int id) {// retourne l'item par rapport a un id
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getId() == id)
				return itemList.get(i);
		}
		return null;
	}

	public ItemModel getInventory(int index) {// retourne l'item de l'inventaire
		return inventaire.getInventory(index);
	}

	public boolean putItem(ItemModel item, int index) {// met un item dans une place
		return inventaire.pushItem(item);
	}

	public boolean pushItem(ItemModel item) {// met un item dans la premiere case libre
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
