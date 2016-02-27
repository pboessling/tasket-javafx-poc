package de.phib.tasket;

import java.net.URL;
import java.util.ResourceBundle;

import de.phib.tasket.ui.DragableTextFieldListCell;
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
	private ListView<String> tasksListView;

	private ObservableList<String> tasks = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tasksListView.setCellFactory(DragableTextFieldListCell.forListView());
		tasksListView.setEditable(true);
		tasksListView.setItems(tasks);

	}

	@FXML
	public void addTask() {
		int selectedIndex = tasksListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			selectedIndex = 0;
		}
		tasks.add(selectedIndex, "New Task");
	}

	@FXML
	public void deleteTask() {
		String selectedItem = tasksListView.getSelectionModel().getSelectedItem();
		tasks.remove(selectedItem);
	}

	@FXML
	public void markAsDoneTask() {
		// TODO: Implement.
	}

}
