package view;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AppMainView extends Application {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private Color bgColor = Color.rgb(127, 172, 255);

    @Override
    public void start(Stage primaryStage) throws Exception {

	primaryStage.setResizable(false);
	Group root = new Group();
	Scene scene = new Scene(root, 854, 480, bgColor);

	root.requestFocus();
	primaryStage.setScene(scene);

	primaryStage.getIcons().add(new Image(new FileInputStream("resources/images/crafting_table_icon.png")));
	primaryStage.setTitle("Crafting System");

	InventoryBarView inventoryBar = new InventoryBarView(scene, isWindows());
	ResearchBarView researchBar = new ResearchBarView(scene, isWindows());
	ItemListView itemList = new ItemListView(scene, isWindows());
	CraftView craftView = new CraftView(scene, isWindows());

	root.getChildren().add(inventoryBar);
	root.getChildren().add(researchBar);
	root.getChildren().add(itemList);
	root.getChildren().add(craftView);

	scene.getStylesheets().add("ResearchBar.css");

	primaryStage.show();

    }

    public static boolean isWindows() {
	return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
	return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
	return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
	return (OS.indexOf("sunos") >= 0);
    }

}
