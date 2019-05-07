package model;

import java.util.ArrayList;
import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javafx.scene.image.Image;

public class StructuresBuilderFromXML extends DefaultHandler {

	private ArrayList<ItemModel> itemList = new ArrayList<>();
	private Hashtable<CraftModel, Integer> craftsList = new Hashtable<>();
	private String currentElement = "";
	private String path = "/images/items/";
	private int currentItemId;
	private int[] currentTriplet = { -1, -1, ItemModel.NOT_AN_ITEM };
	private int[][] currentCraftMatrix = {
		{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM },
		{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM },
		{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM }
	};

	public StructuresBuilderFromXML() {
		super();
	}

	public ArrayList<ItemModel> getItems() throws Exception {
		return this.itemList;
	}

	public Hashtable<CraftModel, Integer> getCrafts() throws Exception {
		return this.craftsList;
	}

	////////////////////////////////////////////////////////////////////
	// Event handlers.arg0
	////////////////////////////////////////////////////////////////////

	@Override
	public void startElement(String uri, String name, String qName, Attributes atts) {
		if (qName.equals("item"))
			this.itemList.add(new ItemModel());
		this.currentElement = qName;
	}

	@Override
	public void endElement(String uri, String name, String qName) {
		if (qName.equals("triplet")) {
			for (int i = 0; i < currentTriplet.length; i++)
				currentCraftMatrix[currentTriplet[0]][currentTriplet[1]] = currentTriplet[2];
			resetTriplet();
		}
		if (qName.equals("craft")) {
			craftsList.put(new CraftModel(currentCraftMatrix), currentItemId);
			resetMatrix();
		}
		this.currentElement = null;
	}

	@Override
	public void characters(char ch[], int start, int length) {

		String value = "";

		for (int i = start; i < start + length; i++) {
			value += ch[i];
		}

		if (this.currentElement != null && this.currentElement.equals("id")) {
			this.itemList.get(this.itemList.size() - 1).setId(Integer.parseInt(value));
			this.currentItemId = Integer.parseInt(value);
		}
		if (this.currentElement != null && this.currentElement.equals("name")) {
			this.itemList.get(this.itemList.size() - 1).setName(value);
		}
		if (this.currentElement != null && this.currentElement.equals("description")) {
			this.itemList.get(this.itemList.size() - 1).setDesciption(value);
		}
		if (this.currentElement != null && this.currentElement.equals("image")) {
			this.itemList.get(this.itemList.size() - 1)
					.setImage(new Image(getClass().getResourceAsStream(path + value)));
		}
		if (this.currentElement != null && this.currentElement.equals("x")) {
			this.currentTriplet[0] = Integer.parseInt(value);
		}
		if (this.currentElement != null && this.currentElement.equals("y")) {
			this.currentTriplet[1] = Integer.parseInt(value);
		}
		if (this.currentElement != null && this.currentElement.equals("id_t")) {
			this.currentTriplet[2] = Integer.parseInt(value);
		}
	}

	private void resetMatrix() {
		for (int i = 0; i < currentCraftMatrix.length; i++)
			for (int j = 0; j < currentCraftMatrix[i].length; j++)
				currentCraftMatrix[i][j] = ItemModel.NOT_AN_ITEM;
	}

	private void resetTriplet() {
		for (int i = 0; i < currentTriplet.length; i++)
			currentTriplet[i] = -1;
	}
}
