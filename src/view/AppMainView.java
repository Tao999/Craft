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
		primaryStage.setScene(scene);
		
		InventoryBar ib = new InventoryBar(scene, 9);
		
		root.getChildren().add(ib);

		primaryStage.show();
		
		/*
		 * Group root = new Group(); Scene scene = new Scene(root, 800, 600,
		 * Color.BLACK); primaryStage.setScene(scene); Group circles = new Group(); for
		 * (int i = 0; i < 30; i++) { Circle circle = new Circle(150, Color.web("white",
		 * 0.05)); circle.setStrokeType(StrokeType.OUTSIDE);
		 * circle.setStroke(Color.web("white", 0.16)); circle.setStrokeWidth(4);
		 * circles.getChildren().add(circle); } Rectangle colors = new
		 * Rectangle(scene.getWidth(), scene.getHeight(), new LinearGradient(0f, 1f, 1f,
		 * 0f, true, CycleMethod.NO_CYCLE, new Stop[]{ new Stop(0,
		 * Color.web("#f8bd55")), new Stop(0.14, Color.web("#c0fe56")), new Stop(0.28,
		 * Color.web("#5dfbc1")), new Stop(0.43, Color.web("#64c2f8")), new Stop(0.57,
		 * Color.web("#be4af7")), new Stop(0.71, Color.web("#ed5fc2")), new Stop(0.85,
		 * Color.web("#ef504c")), new Stop(1, Color.web("#f2660f")),}));
		 * colors.widthProperty().bind(scene.widthProperty());
		 * colors.heightProperty().bind(scene.heightProperty()); Group blendModeGroup =
		 * new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
		 * Color.BLACK), circles), colors); colors.setBlendMode(BlendMode.OVERLAY);
		 * root.getChildren().add(blendModeGroup); circles.setEffect(new BoxBlur(10, 10,
		 * 3)); Timeline timeline = new Timeline(); for (Node circle :
		 * circles.getChildren()) { timeline.getKeyFrames().addAll( new
		 * KeyFrame(Duration.ZERO, // set start position at 0 new
		 * KeyValue(circle.translateXProperty(), random() * 800), new
		 * KeyValue(circle.translateYProperty(), random() * 600)), new KeyFrame(new
		 * Duration(40000), // set end position at 40s new
		 * KeyValue(circle.translateXProperty(), random() * 800), new
		 * KeyValue(circle.translateYProperty(), random() * 600))); } // play 40s of
		 * animation timeline.play(); primaryStage.show();
		 */
	}

}
