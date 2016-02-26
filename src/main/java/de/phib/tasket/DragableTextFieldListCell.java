package de.phib.tasket;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class DragableTextFieldListCell extends TextFieldListCell<String> {

	public DragableTextFieldListCell() {
		this(null);
	}

	public DragableTextFieldListCell(StringConverter<String> converter) {
		this.getStyleClass().add("text-field-list-cell");
		setConverter(converter);

		setOnDragDetected(event -> {
			if (getItem() == null) {
				return;
			}

			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString(getItem());
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
				ObservableList<String> items = getListView().getItems();
				int draggedIdx = items.indexOf(db.getString());
				int thisIdx = items.indexOf(getItem());

				items.set(draggedIdx, getItem());
				items.set(thisIdx, db.getString());

				List<String> itemscopy = new ArrayList<>(getListView().getItems());
				getListView().getItems().setAll(itemscopy);

				success = true;
			}
			event.setDropCompleted(success);

			event.consume();
		});

		setOnDragDone(DragEvent::consume);
	}

	public static Callback<ListView<String>, ListCell<String>> forListView() {
		return list -> new DragableTextFieldListCell(new DefaultStringConverter());
	}

}