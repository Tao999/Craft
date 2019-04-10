package model;

public class Item {
	static final int NOT_AN_ITEM = -1;
	private int id;
	private String name;
	// private image loll'image;
	String desciption;

	public Item() {
		this.desciption = null;
		this.id = NOT_AN_ITEM;
		this.name = null;
	}

	public Item(int id, String name /* image, */, String description) {
		this.id = id;
		this.name = name;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	@Override
	public String toString() {
		String sTemp = "id : " + this.id;
		sTemp += "\nnom : " + this.name;
		sTemp += "\ndesc : " + this.desciption;
		return sTemp;
	}

}
