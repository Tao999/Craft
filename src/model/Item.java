package model;

public class Item {
	static final int NOT_AN_ITEM = -1;
	private int id;
	private String name;
	// private image loll'image;
	String desciption;

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

	@Override
	public String toString() {
		String sTemp = "id : " + this.id;
		sTemp += "\nnom : " + this.name;
		sTemp += "\ndesc : " + this.desciption;
		return sTemp;
	}

}
