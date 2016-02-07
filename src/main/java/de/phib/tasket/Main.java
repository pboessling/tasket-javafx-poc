package de.phib.tasket;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ToolBar toolBar = new ToolBar(new Button("Add"), new Button("Delete"), new Button("Move up"),
				new Button("Move down"), new Button("Mark as done"));

		ObservableList<String> tasks = FXCollections.observableArrayList();
		tasks.addAll("Task 1", "Task 2", "Task 3");

		ListView<String> tasksListView = new ListView<>();
		tasksListView.setCellFactory(TextFieldListCell.forListView());
		tasksListView.setEditable(true);
		tasksListView.setItems(tasks);

		BorderPane root = new BorderPane();
		root.setTop(toolBar);
		root.setCenter(tasksListView);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Tasket");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
