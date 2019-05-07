package contoller;

import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.InventoryModel;
import model.ItemModel;
import view.InventoryBarView;
import view.InventoryBarView.IBSlot;

public class InventoryController extends Observable {

	private InventoryBarView inventoryBarView;
	private InventoryModel inventoryModel;
	private ArrayList<ItemModel> itemList;

	public InventoryController(InventoryBarView inventoryBarView, InventoryModel inventoryModel,
			ArrayList<ItemModel> itemList) {
		this.inventoryBarView = inventoryBarView;
		this.inventoryModel = inventoryModel;
		this.itemList = itemList;
	}

	void run() {
		for (IBSlot itemSlot : this.inventoryBarView.getIBSlots())
			listenInventorySlot(itemSlot);
	}

	private void listenInventorySlot(final IBSlot itemSlot) {

		itemSlot.setOnDragDetected(MouseEvent -> {
			final Dragboard dragBroard = itemSlot.startDragAndDrop(TransferMode.COPY);
			final ClipboardContent content = new ClipboardContent();
			content.putString("Slot");

			DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
			dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
			content.put(dataFormat, itemSlot.getItemId());
			inventoryModel.pickId(itemSlot.getIndex());

			dragBroard.setContent(content);
			itemSlot.changeItem(ItemModel.NOT_AN_ITEM);
			MouseEvent.consume();
		});

		itemSlot.setOnDragOver(DragEvent -> {
			final Dragboard dragBroard = DragEvent.getDragboard();
			DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
			dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
			if (DragEvent.getGestureSource() != itemSlot && dragBroard.hasContent(dataFormat)) {
				DragEvent.acceptTransferModes(TransferMode.COPY);
			}
			DragEvent.consume();
		});

		itemSlot.setOnDragDropped(DragEvent -> {
			boolean success = false;
			try {
				final Dragboard dragBroard = DragEvent.getDragboard();
				DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
				dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
				int data = (Integer) dragBroard.getContent(dataFormat);
				itemSlot.changeItem(data);
				inventoryModel.putItem(getItemFromId(data), itemSlot.getIndex());

				success = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DragEvent.setDropCompleted(success);
				DragEvent.consume();
			}
		});

		itemSlot.setOnMouseClicked(MouseEvent -> {
			if (MouseEvent.isControlDown())
				itemSlot.changeItem(ItemModel.NOT_AN_ITEM);
		});

		itemSlot.setOnMouseClicked(MouseEvent -> {
			if (MouseEvent.isShiftDown())
				// TODO: mettre auto dans la barre d'inventaire
//				itemSlot.changeItem(ItemModel.NOT_AN_ITEM);
				System.out.println("Touche appuyee avec SHIFT");
		});

	}

	private ItemModel getItemFromId(int itemId) {
		for (ItemModel item : this.itemList)
			if (item.getId() == itemId)
				return item;
		return null;
	}

}
