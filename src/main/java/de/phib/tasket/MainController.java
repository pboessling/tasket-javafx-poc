package de.phib.tasket;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;

public class MainController implements Initializable {

	@FXML
	private Button addButton;

	@FXML
	private Button deleteButton;

	@FXML
	private Button moveUpButton;

	@FXML
	private Button moveDownButton;

	@FXML
	private Button markAsDoneButton;

	@FXML
	private ListView<String> tasksListView;

	private ObservableList<String> tasks = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tasksListView.setCellFactory(TextFieldListCell.forListView());
		tasksListView.setEditable(true);
		tasksListView.setItems(tasks);
	}

	@FXML
	public void addTask() {
		tasks.add(new String("New Task"));
	}

	@FXML
	public void deleteTask() {
		String itemToDelete = tasksListView.getSelectionModel().getSelectedItem();
		tasks.remove(itemToDelete);
	}

	@FXML
	public void moveUpTask() {
		// TODO: Implement.
	}

	@FXML
	public void moveDownTask() {
		// TODO: Implement.
	}

	@FXML
	public void markAsDoneTask() {
		// TODO: Implement.
	}

}
