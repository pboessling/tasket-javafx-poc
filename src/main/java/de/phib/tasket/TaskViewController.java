package de.phib.tasket;

import java.net.URL;
import java.util.ResourceBundle;

import de.phib.tasket.persistence.Task;
import de.phib.tasket.persistence.TaskService;
import de.phib.tasket.ui.TaskListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TaskViewController implements Initializable {

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

	private TaskService taskService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.taskService = new TaskService();

		this.tasks.addAll((this.taskService.findAll()));

		this.tasksListView.setCellFactory(TaskListCell.forListView2());
		this.tasksListView.setEditable(true);
		this.tasksListView.setItems(this.tasks);
	}

	@FXML
	public void addTask() {
		int selectedIndex = this.tasksListView.getSelectionModel().getSelectedIndex();

		if (selectedIndex < 0) {
			selectedIndex = 0;
		}

		Task task = this.taskService.create("New Task");
		this.tasks.add(selectedIndex, task);
	}

	@FXML
	public void deleteTask() {
		Task task = this.tasksListView.getSelectionModel().getSelectedItem();

		this.tasks.remove(task);
		this.taskService.delete(task);
	}

	@FXML
	public void markAsDoneTask() {
		// TODO: Implement.
	}

}