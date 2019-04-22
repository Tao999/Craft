package application;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import contoller.StructuresBuilderFromXML;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Item;
import view.View;

public class ApplicationLauncher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

//		Hashtable<Craft, Integer> craftsList = null;
		ArrayList<Item> itemList = null;

		StructuresBuilderFromXML handler = generateLists();
//		craftsList = handler.getCrafts();
		itemList = handler.getItems();

		View view = new View(itemList);
//		Model model = new Model();

		primaryStage.setResizable(false);
		primaryStage.setScene(view.getScene());
		primaryStage.setTitle("Crafting System");
		primaryStage.getIcons().add(new Image(new FileInputStream("resources/images/crafting_table_icon.png")));
		primaryStage.show();

//		new Controller(view, model).start();

		exitApplication(primaryStage);
	}

	private StructuresBuilderFromXML generateLists() throws Exception {
		XMLReader xr = XMLReaderFactory.createXMLReader();
		StructuresBuilderFromXML handler = new StructuresBuilderFromXML();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		FileReader r = new FileReader("resources/list_items.xml");
		xr.parse(new InputSource(r));
		return handler;
	}

	private void exitApplication(Stage primaryStage) {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
	}

}
