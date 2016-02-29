package de.phib.tasket;

import de.phib.tasket.persistence.PersistenceUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The main class, which starts the application
 * 
 * @author pboessling
 *
 */
public class Main extends Application {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            any command line arguments
	 * @throws Exception
	 *             any exception
	 */
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	/**
	 * Starts the JavaFX application, by setting up the persistence layer, and
	 * loading the scene graph from XML.
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		PersistenceUtil.setUp();

		Font.loadFont(
				getClass().getResource("/de/jensd/fx/glyphs/fontawesome/fontawesome-webfont.ttf").toExternalForm(), 12);

		Parent root = FXMLLoader.load(getClass().getResource("/de/phib/tasket/controller/Tasket.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("Tasket");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Tears down the persistence layer on stop of the JavaFX application.
	 * 
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() throws Exception {
		PersistenceUtil.tearDown();
	}

}
