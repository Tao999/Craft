package contoller;

import java.util.Observable;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.Craft;
import model.Item;
import model.Model;
import view.CraftView;
import view.Slot;

public class CraftController extends Observable {

	// Tuto DnD: https://java.developpez.com/faq/javafx/?page=Drag-n-Drop

	private CraftView craftView;
	private Model model;
	private int[][] craftMatrix = {
		{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
		{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM },
		{ Item.NOT_AN_ITEM, Item.NOT_AN_ITEM, Item.NOT_AN_ITEM }
	};

	public CraftController(CraftView craftView, Model model) {
		this.craftView = craftView;
		this.model = model;
	}

	public void run() {
		listenAllSlots();
	}

	private void listenAllSlots() {
		for (Slot itemSlot : this.craftView.getInventorySlots())
			listenInventorySlot(itemSlot);
		for (Slot craftingSlot : this.craftView.getCraftingSlots())
			listenCraftingSlot(craftingSlot);
		listenRecipiesSlot(this.craftView.getRecipiesSlot());
	}

	private void listenRecipiesSlot(Slot recipiesSlot) {

		recipiesSlot.setOnDragDetected(MouseEvent -> {
			final Dragboard dragBroard = recipiesSlot.startDragAndDrop(TransferMode.COPY);
			final ClipboardContent content = new ClipboardContent();
			content.putString("Slot");

			DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
			dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
			content.put(dataFormat, recipiesSlot.getItemId());

			dragBroard.setContent(content);
			recipiesSlot.changeItem(Item.NOT_AN_ITEM);
			for (Slot craftingSlot : this.craftView.getCraftingSlots())
				craftingSlot.changeItem(Item.NOT_AN_ITEM);
			resetCraftingMatrix();
			MouseEvent.consume();
		});

	}

	private void listenCraftingSlot(Slot craftingSlot) {

		craftingSlot.setOnDragDetected(MouseEvent -> {
			final Dragboard dragBroard = craftingSlot.startDragAndDrop(TransferMode.COPY);
			final ClipboardContent content = new ClipboardContent();
			content.putString("Slot");

			DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
			dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
			content.put(dataFormat, craftingSlot.getItemId());

			dragBroard.setContent(content);
			craftingSlot.changeItem(Item.NOT_AN_ITEM);
			this.craftMatrix[craftingSlot.getIndex()[0]][craftingSlot.getIndex()[1]] = Item.NOT_AN_ITEM;
			MouseEvent.consume();
		});

		craftingSlot.setOnDragOver(DragEvent -> {
			final Dragboard dragBroard = DragEvent.getDragboard();
			DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
			dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
			if (DragEvent.getGestureSource() != craftingSlot && dragBroard.hasContent(dataFormat)) {
				DragEvent.acceptTransferModes(TransferMode.COPY);
			}
			DragEvent.consume();
		});

		craftingSlot.setOnDragDropped(DragEvent -> {
			boolean success = false;
			try {
				final Dragboard dragBroard = DragEvent.getDragboard();
				DataFormat dataFormat = DataFormat.lookupMimeType(Integer.class.getName());
				dataFormat = (dataFormat == null) ? new DataFormat(Integer.class.getName()) : dataFormat;
				int data = (Integer) dragBroard.getContent(dataFormat);
				craftingSlot.changeItem(data);
				majCraft(craftingSlot, data);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DragEvent.setDropCompleted(success);
				DragEvent.consume();
			}
		});

		craftingSlot.setOnMouseClicked(MouseEvent -> {
			if (MouseEvent.isControlDown())
				craftingSlot.changeItem(Item.NOT_AN_ITEM);
			majCraft(craftingSlot, Item.NOT_AN_ITEM);
		});
	}

	private void listenInventorySlot(final Slot itemSlot) {

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

	private void majCraft(Slot craftingSlot, int data) {
		craftMatrix[craftingSlot.getIndex()[0]][craftingSlot.getIndex()[1]] = data;
		this.craftView.getRecipiesSlot().changeItem(model.craftRecipe(new Craft(craftMatrix)));
	}

	private void resetCraftingMatrix() {
		for (int i = 0; i < craftMatrix.length; i++)
			for (int j = 0; j < craftMatrix[0].length; j++)
				craftMatrix[i][j] = Item.NOT_AN_ITEM;
	}

}
