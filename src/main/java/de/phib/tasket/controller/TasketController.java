package de.phib.tasket.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import de.phib.tasket.persistence.Backlog;
import de.phib.tasket.persistence.BacklogService;
import de.phib.tasket.persistence.Task;
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

	private Backlog backlog;

	private ObservableList<Task> tasks = FXCollections.observableArrayList();

	private BacklogService backlogService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeButtons();
		initializeBacklog();
		initializeListView();
	}

	private void initializeButtons() {
		this.addButton.setFont(Font.font("FontAwesome"));
		this.addButton.setText("\uf067");
		this.deleteButton.setFont(Font.font("FontAwesome"));
		this.deleteButton.setText("\uf068");
	}

	private void initializeBacklog() {
		this.backlogService = new BacklogService();
		this.backlog = new Backlog();

		List<Backlog> backlogs = this.backlogService.findAllBacklogs();
		if (!backlogs.isEmpty()) {
			this.backlog = backlogs.get(0);
		} else {
			this.backlog = this.backlogService.createBacklog("Backlog");
		}

		List<Task> backlogTasks = backlog.getTasks();
		if (!backlogTasks.isEmpty()) {
			this.tasks = FXCollections.observableList(backlogTasks);
		}
	}

	private void initializeListView() {
		this.tasksListView.setCellFactory(TaskListCell.forListView2(this.backlog));
		this.tasksListView.setEditable(true);
		this.tasksListView.setItems(this.tasks);

		this.tasksListView.setOnEditCommit(new EventHandler<ListView.EditEvent<Task>>() {

			@Override
			public void handle(ListView.EditEvent<Task> event) {
				int editIndex = event.getIndex();
				Task currentTask = tasksListView.getItems().get(editIndex);

				Task newTask = event.getNewValue();
				currentTask.setTitle(newTask.getTitle());

				TasketController.this.backlog = backlogService.updateBacklog(currentTask.getBacklog());
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

		Task task = new Task("New Task");
		task.setBacklog(this.backlog);
		this.tasks.add(insertionIndex, task);
		this.backlog = this.backlogService.updateBacklog(this.backlog);
	}

	@FXML
	public void deleteTask() {
		Task task = this.tasksListView.getSelectionModel().getSelectedItem();

		this.tasks.remove(task);
		// TODO: Is it necessary to call task.setBacklog(null) first?
		this.backlog = this.backlogService.updateBacklog(backlog);
	}

}
