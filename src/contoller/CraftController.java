package contoller;

import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.Item;
import model.Model;
import view.CraftView;
import view.Slot;

public class CraftController {

	// Tuto DnD: https://java.developpez.com/faq/javafx/?page=Drag-n-Drop

	private CraftView craftView;
	@SuppressWarnings("unused")
	private Model model;

	public CraftController(CraftView craftView, Model model) {
		this.craftView = craftView;
		this.model = model;
	}

	public void run() {
		listenAllSlots();
	}

	private void listenAllSlots() {
		for (Slot itemSlot : this.craftView.getInventorySlots()) {
			listenSlot(itemSlot);
		}
	}

	private void listenSlot(final Slot itemSlot) {

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

//		itemSlot.setOnKeyPressed(KeyEvent -> {
//			System.out.println("oui");
//			if (KeyEvent.getCode() == KeyCode.ENTER) {
//				System.out.println("Delete Pressed on Item");
//			}
//		});
	}

}
