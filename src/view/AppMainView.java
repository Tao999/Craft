package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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

	InventoryBar inventoryBar = new InventoryBar(scene);
	ResearchBar researchBar = new ResearchBar(scene);

	root.getChildren().add(inventoryBar);
	root.getChildren().add(researchBar);

	scene.getStylesheets().add("/view/ResearchBar.css");

	primaryStage.show();
    }

}
