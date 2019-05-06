package contoller;

import java.util.Observable;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.Item;
import view.InventoryBarView;
import view.InventoryBarView.IBSlot;

public class InventoryController extends Observable {

	private InventoryBarView inventoryBarView;

	public InventoryController(InventoryBarView inventoryBarView) {
		this.inventoryBarView = inventoryBarView;
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

			dragBroard.setContent(content);
			itemSlot.changeItem(Item.NOT_AN_ITEM);
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
				itemSlot.changeItem(Item.NOT_AN_ITEM);
		});

	}

}
