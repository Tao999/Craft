package model;

import javafx.scene.image.Image;

public class Item {
	public static final int NOT_AN_ITEM = -1;
	private int id;
	private String name;
	private Image image;
	String desciption;

	public Item() {
		this.desciption = null;
		this.id = NOT_AN_ITEM;
		this.name = null;
	}

	public Item(int id, String name, String description, Image image) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.desciption = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return desciption;
	}

	public Image getImage() {
		return this.image;
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

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		String sTemp = "id : " + this.id;
		sTemp += "\nnom : " + this.name;
		sTemp += "\ndesc : " + this.desciption;
		return sTemp;
	}

}
