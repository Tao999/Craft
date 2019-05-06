package application;

import java.util.ArrayList;
import java.util.Hashtable;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import contoller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Craft;
import model.Item;
import model.Model;
import model.StructuresBuilderFromXML;
import view.View;

public class ApplicationLauncher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		Template MVC avec JavaFX:
//		https://github.com/amirdine/personal-projects/tree/master/java/calculatrice/src/com/github/amirdine/project/calculator

		ArrayList<Item> itemList = null;
		Hashtable<Craft, Integer> craftsList = null;

		StructuresBuilderFromXML handler = generateLists();
		itemList = handler.getItems();
		craftsList = handler.getCrafts();

		View view = new View(itemList);
		Model model = new Model(itemList, craftsList);

		primaryStage.setResizable(false);
		primaryStage.setScene(view.getScene());
		primaryStage.setTitle("Crafting System");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/crafting_table_icon.png")));
		primaryStage.show();

		new Controller(view, model).start();

		exitApplication(primaryStage);
	}

	private StructuresBuilderFromXML generateLists() throws Exception {
		XMLReader xr = XMLReaderFactory.createXMLReader();
		StructuresBuilderFromXML handler = new StructuresBuilderFromXML();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		InputSource in = new InputSource(getClass().getClassLoader().getResource("list_items.xml").toExternalForm());

		xr.parse(in);
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