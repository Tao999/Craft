package contoller;

import java.util.Observable;

import view.ItemListView;
import view.ResearchBarView;

public class ResearchBarController extends Observable {

	private ItemListView itemListView;
	private ResearchBarView researchBarView;
	private CraftController cC;

	public ResearchBarController(ResearchBarView craftView, ItemListView itemListView, CraftController cC) {
		this.researchBarView = craftView;
		this.itemListView = itemListView;
		this.cC = cC;
	}

	void run() {
		attachEvent();
	}

	private void attachEvent() {
		researchBarView.getTextFieldText().addListener((observable, oldValue, newValue) -> {
			cC.run();
			itemListView.searchItem(newValue);
		});
	}

}
