package de.phib.tasket;

import de.phib.tasket.persistence.PersistenceUtil;
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
		PersistenceUtil.setUp();

		Parent root = FXMLLoader.load(getClass().getResource("/de/phib/tasket/controller/Tasket.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("Tasket");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		PersistenceUtil.tearDown();
	}

}
