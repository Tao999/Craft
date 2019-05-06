package contoller;

import model.Model;
import view.View;

public class Controller {

	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	public void start() {
		// TODO Auto-generated method stub
		ItemListController ilC = new ItemListController(view.itemListView);
		CraftController cC = new CraftController(view.craftView, model);
		ResearchBarController rbC = new ResearchBarController(view.researchBarView, view.itemListView, ilC);
//		InventoryController iC = new InventoryController();

		ilC.run();
		rbC.run();
		cC.run();
//		iC.run();
	}

}
