package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import model.Craft;
import model.Item;
import model.Model;
import model.StructuresBuilderFromXML;

class ModelTest {

	public Model model;

	@BeforeEach
	void setUp() throws Exception {
		ArrayList<Item> itemList = null;
		Hashtable<Craft, Integer> craftsList = null;

		StructuresBuilderFromXML handler = generateLists();
		itemList = handler.getItems();
		craftsList = handler.getCrafts();

		model = new Model(itemList, craftsList);

	}

	private StructuresBuilderFromXML generateLists() throws Exception {
		XMLReader xr = XMLReaderFactory.createXMLReader();
		StructuresBuilderFromXML handler = new StructuresBuilderFromXML();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		InputSource in = new InputSource(getClass().getClassLoader().getResource("list_items.xml").toExternalForm());

		xr.parse(in);
		return handler;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testCraftRecipe1() {
		int[][] craftMatrix = { { Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM } };

		assertEquals(Item.NOT_AN_ITEM, model.craftRecipe(new Craft(craftMatrix)));
	}

	@Test
	public void testCraftRecipe2() {
		int[][] craftMatrix = { { 0, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM } };

		assertEquals(Item.NOT_AN_ITEM, model.craftRecipe(new Craft(craftMatrix)));
	}

	@Test
	public void testCraftRecipe3() {
		int[][] craftMatrix = { { 5, 5, Item.NOT_AN_ITEM }, { Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM } };

		assertEquals(280, model.craftRecipe(new Craft(craftMatrix)));
	}

	@Test
	public void testCreateCraft1() {
		int[][] craftMatrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		assertEquals(Item.NOT_AN_ITEM, model.craftRecipe(new Craft(craftMatrix)));
	}

	@Test
	public void testCreateCraft2() {
		int[][] craftMatrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		model.createCraft(new Craft(craftMatrix), 2);
		assertEquals(2, model.craftRecipe(new Craft(craftMatrix)));
	}

}
