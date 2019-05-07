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

import model.CraftModel;
import model.ItemModel;
import model.Model;
import model.StructuresBuilderFromXML;

class ModelTest {

	public Model model;

	@BeforeEach
	void setUp() throws Exception {
		ArrayList<ItemModel> itemList = null;
		Hashtable<CraftModel, Integer> craftsList = null;

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
		int[][] craftMatrix = { { ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM },
				{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM },
				{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM } };

		assertEquals(ItemModel.NOT_AN_ITEM, model.craftRecipe(new CraftModel(craftMatrix)));
	}

	@Test
	public void testCraftRecipe2() {
		int[][] craftMatrix = { { 0, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM },
				{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM },
				{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM } };

		assertEquals(ItemModel.NOT_AN_ITEM, model.craftRecipe(new CraftModel(craftMatrix)));
	}

	@Test
	public void testCraftRecipe3() {
		int[][] craftMatrix = { { 5, 5, ItemModel.NOT_AN_ITEM }, { ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM },
				{ ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM, ItemModel.NOT_AN_ITEM } };

		assertEquals(280, model.craftRecipe(new CraftModel(craftMatrix)));
	}

	@Test
	public void testCreateCraft1() {
		int[][] craftMatrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		assertEquals(ItemModel.NOT_AN_ITEM, model.craftRecipe(new CraftModel(craftMatrix)));
	}

	@Test
	public void testCreateCraft2() {
		int[][] craftMatrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		model.createCraft(new CraftModel(craftMatrix), 2);
		assertEquals(2, model.craftRecipe(new CraftModel(craftMatrix)));
	}

}
