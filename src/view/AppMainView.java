package view;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AppMainView extends Application {

    private Color bgColor = Color.rgb(127, 172, 255);

    @Override
    public void start(Stage primaryStage) throws Exception {

	Group root = new Group();
	Scene scene = new Scene(root, 854, 480, bgColor);

	root.requestFocus();
	primaryStage.setScene(scene);
	
	primaryStage.getIcons().add(new Image(new FileInputStream("resources/images/crafting_table_icon.png")));
	primaryStage.setTitle("Crafting System");
	
	InventoryBar inventoryBar = new InventoryBar(scene);
	ResearchBar researchBar = new ResearchBar(scene);

	root.getChildren().add(inventoryBar);
	root.getChildren().add(researchBar);
	
	scene.getStylesheets().add("ResearchBar.css");

	primaryStage.show();
    }

}
