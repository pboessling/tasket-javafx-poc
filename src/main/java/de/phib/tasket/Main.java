package de.phib.tasket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Tasket.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Tasket");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
