package model;

import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class CraftsListBuilder extends DefaultHandler {

	private Hashtable<Craft, Integer> craftsList;
	private int currentItemId;
	private String currentElement = "";
	private int[] currentTriplet = {
		-1,
		-1,
		Item.NOT_AN_ITEM
	};
	private int[][] currentCraftMatrix = {
		{
			Item.NOT_AN_ITEM,
			Item.NOT_AN_ITEM,
			Item.NOT_AN_ITEM
		},
		{
			Item.NOT_AN_ITEM,
			Item.NOT_AN_ITEM,
			Item.NOT_AN_ITEM
		},
		{
			Item.NOT_AN_ITEM,
			Item.NOT_AN_ITEM,
			Item.NOT_AN_ITEM
		}
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
		if (qName.equals("triplet")) {
			for (int i = 0; i < currentTriplet.length; i++)
				currentCraftMatrix[currentTriplet[0]][currentTriplet[1]] = currentTriplet[2];
			resetTriplet();
		}
		if (qName.equals("craft")) {
			craftsList.put(new Craft(currentCraftMatrix), currentItemId);
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
		if (this.currentElement.equals("x"))
			this.currentTriplet[0] = Integer.parseInt(value);
		if (this.currentElement.equals("y"))
			this.currentTriplet[1] = Integer.parseInt(value);
		if (this.currentElement.equals("id_t"))
			this.currentTriplet[2] = Integer.parseInt(value);
	}

	private void resetMatrix() {
		for (int i = 0; i < currentCraftMatrix.length; i++)
			for (int j = 0; j < currentCraftMatrix[i].length; j++)
				currentCraftMatrix[i][j] = Item.NOT_AN_ITEM;
	}

	private void resetTriplet() {
		for (int i = 0; i < currentTriplet.length; i++)
			currentTriplet[i] = -1;
	}

}
