package contoller;

import java.util.Observable;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.Model;
import view.ItemListSlot;
import view.ItemListView;

public class ItemListController extends Observable {

	// Tuto DnD: https://java.developpez.com/faq/javafx/?page=Drag-n-Drop

	private ItemListView itemListView;
	@SuppressWarnings("unused")
	private Model model;

	public ItemListController(ItemListView itemListView, Model model) {
		this.itemListView = itemListView;
		this.model = model;
	}

	public void run() {
		listenAllItems();
	}

	private void listenAllItems() {
		for (ItemListSlot itemSlot : this.itemListView.getItems()) {
			listenItem(itemSlot);
		}
	}

	private void listenItem(final ItemListSlot itemSlot) {

		itemSlot.setOnDragDetected(MouseEvent -> {
			final Dragboard dragBroard = itemSlot.startDragAndDrop(TransferMode.COPY);
			final ClipboardContent content = new ClipboardContent();
			content.putString("Slot");

			DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
			dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
			content.put(dataFormat, itemSlot.getItem().getId());

			dragBroard.setContent(content);
			MouseEvent.consume();
		});
	}

}
