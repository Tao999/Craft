package contoller;

import java.util.Observable;

import view.ItemListView;
import view.ResearchBarView;

public class ResearchBarController extends Observable {

	private ItemListView itemListView;
	private ResearchBarView researchBarView;
	private ItemListController ilC;

	public ResearchBarController(ResearchBarView craftView, ItemListView itemListView, ItemListController ilC) {
		this.researchBarView = craftView;
		this.itemListView = itemListView;
		this.ilC = ilC;
	}

	void run() {
		attachEvent();
	}

	private void attachEvent() {
		researchBarView.getTextFieldText().addListener((observable, oldValue, newValue) -> {
			itemListView.searchItem(newValue);
			ilC.run();
		});
	}

}
