package de.phib.tasket;

import java.net.URL;
import java.util.ResourceBundle;

import de.phib.tasket.persistence.Task;
import de.phib.tasket.ui.TaskListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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
	private ListView<Task> tasksListView;

	private ObservableList<Task> tasks = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tasksListView.setCellFactory(TaskListCell.forListView2());
		tasksListView.setEditable(true);
		tasksListView.setItems(tasks);

	}

	@FXML
	public void addTask() {
		int selectedIndex = tasksListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			selectedIndex = 0;
		}
		tasks.add(selectedIndex, new Task("New Task"));
	}

	@FXML
	public void deleteTask() {
		Task selectedItem = tasksListView.getSelectionModel().getSelectedItem();
		tasks.remove(selectedItem);
	}

	@FXML
	public void markAsDoneTask() {
		// TODO: Implement.
	}

}
