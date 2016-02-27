package de.phib.tasket.ui;

import de.phib.tasket.persistence.Task;
import javafx.util.StringConverter;

public class TaskStringConverter extends StringConverter<Task> {

	@Override
	public String toString(Task value) {
		return (value != null) ? value.getTitle() : "";
	}

	@Override
	public Task fromString(String value) {
		return new Task(value);
	}
}