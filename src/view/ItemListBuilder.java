package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javafx.scene.image.Image;
import model.Item;

public class ItemListBuilder extends DefaultHandler {

    private ArrayList<Item> itemList = new ArrayList<>();
    private String currentElement = "";

    public ItemListBuilder() {
	super();
    }

    public ArrayList<Item> getItems() throws Exception {
	return this.itemList;
    }

    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////

    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {
	if (qName.equals("item"))
	    this.itemList.add(new Item());
	this.currentElement = qName;
    }

    @Override
    public void endElement(String uri, String name, String qName) {
	this.currentElement = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
	String value = "";
	for (int i = start; i < start + length; i++) {
	    value += ch[i];
	}
	if (this.currentElement != null && this.currentElement.equals("id"))
	    this.itemList.get(this.itemList.size() - 1).setId(Integer.parseInt(value));
	else if (this.currentElement != null && this.currentElement.equals("name"))
	    this.itemList.get(this.itemList.size() - 1).setName(value);
	else if (this.currentElement != null && this.currentElement.equals("description"))
	    this.itemList.get(this.itemList.size() - 1).setDesciption(value);
	if (this.currentElement != null && this.currentElement.equals("image"))
	    try {
		this.itemList.get(this.itemList.size() - 1)
			.setImage(new Image(new FileInputStream("../resources/" + value)));
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }
    }

}