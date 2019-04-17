package model;

import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class CraftsListBuilder extends DefaultHandler {

	private Hashtable<Craft, Integer> craftsList;
	private Craft currentCraft;
	private int currentItemId;
	private String currentElement = "";
	private int[] currentTriplet;
	private int[][] currentCraftMatrix = {
			{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
			{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
			{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM }
		};

	public CraftsListBuilder() {
		super();
	}

	public Hashtable<Craft, Integer> getCrafts() throws Exception {
		return this.craftsList;
	}

	////////////////////////////////////////////////////////////////////
	// Event handlers.arg0
	////////////////////////////////////////////////////////////////////

	@Override
	public void startElement(String uri, String name, String qName, Attributes atts) {
		this.currentElement = qName;
	}

	@Override
	public void endElement(String uri, String name, String qName) {
		if (qName.equals("craft")) {
			craftsList.put(currentCraft, currentItemId);
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
		if (this.currentElement.equals("id"))
			this.currentItemId = Integer.parseInt(value);
		if (this.currentElement.equals(""))
		// TODO
	}

	private void resetMatrix() {
		for (int i = 0; i < currentCraftMatrix.length; i++)
			for (int j = 0; j < currentCraftMatrix[i].length; j++)
				currentCraftMatrix[i][j] = Item.NOT_AN_ITEM;
	}

}
