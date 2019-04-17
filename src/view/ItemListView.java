package view;

import java.io.FileReader;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Item;

public class ItemListView extends Group {

	private double lX;
	private double width;

	public ItemListView(Scene scene, boolean isWindows) {

		ArrayList<Item> itemList = null;

		try {
			XMLReader xr = XMLReaderFactory.createXMLReader();
			ItemListBuilder handler = new ItemListBuilder();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);

			FileReader r = new FileReader("resources/list_items.xml");
			xr.parse(new InputSource(r));

			itemList = handler.getItems();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int offset = (isWindows) ? 10 : 0;
		width = (scene.getWidth() + offset) / 3;
		lX = width * 2;

		Rectangle r = new Rectangle(width, scene.getHeight() + offset);
		InnerShadow is = new InnerShadow();

		is.setOffsetX(5.);
		is.setOffsetY(5.);

		r.setFill(Color.rgb(50, 50, 50, 0.8));
		r.setEffect(is);

		this.setOnMouseDragged(null);
		this.getChildren().add(r);
		this.getChildren().add(createList(width, scene.getHeight(), itemList));
		this.setLayoutX(lX);

	}

	private Group createList(double width, double height, ArrayList<Item> itemList) {
		Group gp = new Group();
		ScrollPane sp = new ScrollPane();

		TilePane tp = new TilePane();

		int nbItems = itemList.size();
		int xOffset = 15;
		int yOffset = 15;
		int slotSize = 40;
		int slotMargin = 10;
		int imageMargin = 3;
		int nbRows = (int) (width / (slotSize + slotMargin));

		tp.setOrientation(Orientation.HORIZONTAL);
		tp.setTileAlignment(Pos.TOP_LEFT);
		tp.setPrefRows(nbRows);
		tp.setHgap(slotMargin);
		tp.setVgap(slotMargin);

		for (int i = 0; i < nbItems; i++) {

			Group slot = new Group();
			Rectangle r = new Rectangle(slotSize, slotSize);
			ImageView iv = new ImageView(itemList.get(i).getImage());

			r.setFill(Color.rgb(50, 50, 50, 0.8));
			r.setArcWidth(5);
			r.setArcHeight(5);
			r.setStroke(Color.rgb(198, 198, 198));
			r.setStrokeWidth(2.);
			r.setStrokeType(StrokeType.INSIDE);

			iv.setX(imageMargin);
			iv.setY(imageMargin);
			iv.setFitHeight(slotSize - 2 * imageMargin);
			iv.setFitWidth(slotSize - 2 * imageMargin);

			slot.getChildren().add(r);
			slot.getChildren().add(iv);
			tp.getChildren().add(slot);
		}

		sp.setContent(tp);
		sp.setPannable(true);
		sp.setPrefSize(width, height - 15);

		gp.getChildren().add(sp);
		gp.setLayoutX(xOffset);
		gp.setLayoutY(yOffset);
		return gp;
	}

}
