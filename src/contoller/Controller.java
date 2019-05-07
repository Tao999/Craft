package contoller;

import java.util.ArrayList;

import model.ItemModel;
import model.Model;
import view.View;

public class Controller {

	private View view;
	private Model model;
	private ArrayList<ItemModel> itemList;

	public Controller(View view, Model model, ArrayList<ItemModel> itemList) {
		this.view = view;
		this.model = model;
		this.itemList = itemList;
	}

	public void start() {
		// TODO Auto-generated method stub
		ItemListController ilC = new ItemListController(view.itemListView);
		CraftController cC = new CraftController(view.craftView, model);
		ResearchBarController rbC = new ResearchBarController(view.researchBarView, view.itemListView, ilC);
		InventoryController iC = new InventoryController(view.inventoryBarView, model.inventaire, itemList);

		ilC.run();
		rbC.run();
		cC.run();
		iC.run();
	}

}
