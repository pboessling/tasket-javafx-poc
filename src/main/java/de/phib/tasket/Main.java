package de.phib.tasket;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ObservableList<String> tasks = FXCollections.observableArrayList();
		tasks.addAll("Task 1", "Task 2", "Task 3");

		ListView<String> tasksListView = new ListView<>();
		tasksListView.setCellFactory((param) -> {
			return new TaskCell();
		});
		tasksListView.setItems(tasks);

		StackPane root = new StackPane();
		root.getChildren().add(tasksListView);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Tasket");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static class TaskCell extends ListCell<String> {

		private CheckBox checkbox = new CheckBox();

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);

			if (item != null && !empty) {
				setText(item);
				setGraphic(checkbox);
			}
		}

	}
}
