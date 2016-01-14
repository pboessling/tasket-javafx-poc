package de.phib.tasket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label label = new Label("Hello World!");

		StackPane root = new StackPane();
		root.getChildren().add(label);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Tasket");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
