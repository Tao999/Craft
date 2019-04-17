package model;

import java.util.ArrayList;

public class Craft {

	private class Trouple {
		private int x;
		private int y;
		private int id;

		public Trouple(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getId() {
			return id;
		}
	}

	private ArrayList<Trouple> craft;

	public Craft() {
		craft = null;
	}

	public Craft(int[][] tabCraft) {
		craft = new ArrayList<Trouple>();

		int nbColVide = 0;
		int nbRowVide = 0;
		boolean verif = true;

		for (int i = 0; i < 3 && verif; i++) {
			if (tabCraft[i][0] == Item.NOT_AN_ITEM && tabCraft[i][1] == Item.NOT_AN_ITEM
					&& tabCraft[i][2] == Item.NOT_AN_ITEM) {
				nbColVide++;
			} else
				verif = false;
		}

		verif = true;

		for (int i = 0; i < 3 && verif; i++) {
			if (tabCraft[0][i] == Item.NOT_AN_ITEM && tabCraft[1][i] == Item.NOT_AN_ITEM
					&& tabCraft[2][i] == Item.NOT_AN_ITEM) {
				nbRowVide++;
			} else
				verif = false;
		}

		for (int i = 0; i < 3 - nbColVide; i++) {
			for (int j = 0; j < 3 - nbRowVide; j++) {
				if (tabCraft[i + nbColVide][j + nbRowVide] != Item.NOT_AN_ITEM)
					craft.add(new Trouple(i, j, tabCraft[i + nbColVide][j + nbRowVide]));
			}
		}

		craft.trimToSize();
	}

	@Override
	public int hashCode() {
		if (craft.isEmpty())
			return 0;
		int code = 0;
		for (int i = 0; i < craft.size(); i++) {
			code += (craft.get(i).getX() * 3 + craft.get(i).getY()) * 60 * craft.get(i).getId();
		}
		return code;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.hashCode() == obj.hashCode();
	}

	public String toString() {
		String sTemp = "";
		for (int i = 0; i < craft.size(); i++) {
			sTemp += craft.get(i).getX() + ", " + craft.get(i).getY() + " : " + craft.get(i).getId() + "\r\n";
		}
		return sTemp;
	}
}
