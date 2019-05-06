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
import view.View;

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
		int[][] craftMatrix = { { 0, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM } };

		assertEquals(Item.NOT_AN_ITEM, model.craftRecipe(new Craft(craftMatrix)));
	}

	public void testCraftRecipe2() {
		int[][] craftMatrix = { { 5, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM }, { 5, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
				{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM } };

		assertEquals(280, model.craftRecipe(new Craft(craftMatrix)));
	}

	@Test
	public void testCreateCraft() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testPushItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testEraseInventory() {
		fail("Not yet implemented");
	}

	@Test
	public void testPickId() {
		fail("Not yet implemented");
	}

}
