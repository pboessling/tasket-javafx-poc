package de.phib.tasket.controller;

import java.net.URL;
import java.util.ResourceBundle;

import de.phib.tasket.persistence.Task;
import de.phib.tasket.persistence.TaskService;
import de.phib.tasket.ui.TaskListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;

public class TasketController implements Initializable {

	@FXML
	private Button addButton;

	@FXML
	private Button deleteButton;

	@FXML
	private ListView<Task> tasksListView;

	private ObservableList<Task> tasks = FXCollections.observableArrayList();

	private TaskService taskService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.addButton.setFont(Font.font("FontAwesome"));
		this.addButton.setText("\uf067");
		this.deleteButton.setFont(Font.font("FontAwesome"));
		this.deleteButton.setText("\uf068");

		this.taskService = new TaskService();

		this.tasks.addAll((this.taskService.findAll()));

		this.tasksListView.setCellFactory(TaskListCell.forListView2());
		this.tasksListView.setEditable(true);
		this.tasksListView.setItems(this.tasks);

		this.tasksListView.setOnEditCommit(new EventHandler<ListView.EditEvent<Task>>() {

			@Override
			public void handle(ListView.EditEvent<Task> event) {
				int editIndex = event.getIndex();
				Task currentTask = tasksListView.getItems().get(editIndex);

				Task newTask = event.getNewValue();
				currentTask.setTitle(newTask.getTitle());

				taskService.update(currentTask);
			}
		});
	}

	@FXML
	public void addTask() {
		int insertionIndex = this.tasks.size();
		int selectedIndex = this.tasksListView.getSelectionModel().getSelectedIndex();

		if (selectedIndex > 0) {
			insertionIndex = selectedIndex + 1;
		}

		Task task = this.taskService.create("New Task");
		this.tasks.add(insertionIndex, task);
	}

	@FXML
	public void deleteTask() {
		Task task = this.tasksListView.getSelectionModel().getSelectedItem();

		this.tasks.remove(task);
		this.taskService.delete(task);
	}

}
