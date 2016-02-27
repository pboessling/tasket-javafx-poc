package de.phib.tasket.ui;

import java.util.ArrayList;
import java.util.List;

import de.phib.tasket.persistence.Task;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class TaskListCell extends TextFieldListCell<Task> {

	private static final DataFormat DATA_FORMAT_TASK = new DataFormat("application/x-java-task");

	public TaskListCell() {
		this(null);
	}

	public TaskListCell(StringConverter<Task> converter) {
		this.getStyleClass().add("text-field-list-cell");
		setConverter(converter);

		setOnDragDetected(event -> {
			if (getItem() == null) {
				return;
			}

			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString(getItem().getTitle());
			content.put(DATA_FORMAT_TASK, getItem());
			dragboard.setContent(content);

			event.consume();
		});

		setOnDragOver(event -> {
			if (event.getGestureSource() != this && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}

			event.consume();
		});

		setOnDragEntered(event -> {
			if (event.getGestureSource() != this && event.getDragboard().hasString()) {
				setOpacity(0.3);
			}
		});

		setOnDragExited(event -> {
			if (event.getGestureSource() != this && event.getDragboard().hasString()) {
				setOpacity(1);
			}
		});

		setOnDragDropped(event -> {
			if (getItem() == null) {
				return;
			}

			Dragboard db = event.getDragboard();
			boolean success = false;

			if (db.hasString()) {
				ObservableList<Task> items = getListView().getItems();
				Task draggedItem = (Task) db.getContent(DATA_FORMAT_TASK);
				int draggedIdx = items.indexOf(draggedItem);
				int thisIdx = items.indexOf(getItem());

				items.set(draggedIdx, getItem());
				items.set(thisIdx, draggedItem);

				List<Task> itemscopy = new ArrayList<>(getListView().getItems());
				getListView().getItems().setAll(itemscopy);

				success = true;
			}
			event.setDropCompleted(success);

			event.consume();
		});

		setOnDragDone(DragEvent::consume);
	}

	public static Callback<ListView<Task>, ListCell<Task>> forListView2() {
		return list -> new TaskListCell(new TaskStringConverter());
	}

}