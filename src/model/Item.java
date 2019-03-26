package model;

public class Item {
	static final int NOT_AN_ITEM = -1;
	private int id;
	private String name;
	// private image loll'image;
	private int[][] recipe;
	String desciption;

	public Item(int id, String name /* image, */, String description) {
		this.id = id;
		this.name = name;
		this.recipe = null;
		// this.image = image;
		this.desciption = description;
	}

	public Item(int id, String name /* image, */, String description,
			int[][] recipe) {
		this.id = id;
		this.name = name;
		this.recipe = recipe;
		// this.image = image;
		this.desciption = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[][] getRecipe() {
		return recipe;
	}

	public void setRecipe(int[][] recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		String sTemp = "id : " + this.id;
		sTemp += "\nnom : " + this.name;
		sTemp += "\ndesc : " + this.desciption;
		if (recipe != null) {
			sTemp += "\nrecette :\n+-+-+-+\n";
			for (int i = 0; i < 3; i++) {
				sTemp += "|";
				for (int j = 0; j < 3; j++) {
					sTemp += recipe[i][j];
					sTemp += "|";
				}
				sTemp += "\n+-+-+-+\n";
			}
		} else
			sTemp += "\npas de recette pour l'obtenir";
		return sTemp;
	}

}
