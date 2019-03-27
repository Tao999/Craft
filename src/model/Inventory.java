package model;

public class Inventory {
	static final int NB_ITEM = 9;

	private Item[] inventory;

	public Inventory() {
		this.inventory = new Item[NB_ITEM];
	}

	public Item getInventory(int index) {// retourne l'item de l'inventaire
											// choisi (preferer pickInventory)
		if (index >= NB_ITEM || index < 0)
			return null;
		return this.inventory[index];
	}

	public boolean putItem(Item item, int index) {// met un item dans une place
													// choisi, si prise,
													// retourne false
		if (index >= NB_ITEM || index < 0)
			return false;
		this.inventory[index] = item;
		return true;
	}

	public boolean pushItem(Item item) {// met un item dans la premiere case libre
										// libre, sinon retourne false
		int iScan = 0;
		while (inventory[iScan] != null) {
			iScan++;
			if (iScan == NB_ITEM)
				return false;
		}
		inventory[iScan] = item;
		return true;
	}

	public void eraseInventory() {// efface l'inventaire
		for (int i = 0; i < NB_ITEM; i++) {
			this.inventory[i] = null;
		}
	}

	public int pickId(int index) {// retourne l'id de l'item choisi, et
									// l'enleve de l'inventaire
		if (index >= NB_ITEM || index < 0)
			return Item.NOT_AN_ITEM;
		int temp = this.inventory[index].getId();
		this.inventory[index] = null;
		return temp;
	}

	public int getId(int index) {// retoure l'id de l'item choisi
		if (index >= NB_ITEM || index < 0)
			return Item.NOT_AN_ITEM;
		return this.inventory[index].getId();
	}
	
	@Override
	public String toString() {
		String sTemp = "";
		String sLineSeparator = "+";
		for (int k = 0; k < NB_ITEM; k++)
			sLineSeparator += "-+";
		sTemp += sLineSeparator;
		sTemp += "\r\n|";
		for (int i = 0; i < NB_ITEM; i++) {
			if (this.inventory[i] == null)
				sTemp += "/|";
			else
				sTemp += this.inventory[i].getId() + "|";
		}
		sTemp += "\r\n";
		sTemp += sLineSeparator;

		return sTemp;
	}
}
