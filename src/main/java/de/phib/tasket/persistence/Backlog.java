package de.phib.tasket.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
@NamedQuery(name = Backlog.QUERY_FIND_ALL_BACKLOGS, query = "SELECT b FROM Backlog b")
public class Backlog implements Serializable {

	private static final long serialVersionUID = -7227768341746587995L;

	public static final String QUERY_FIND_ALL_BACKLOGS = "findAllBacklogs";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int backlogId;

	private String title;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "backlog", orphanRemoval = true)
	@OrderColumn
	private List<Task> tasks = new ArrayList<>();

	public int getBacklogId() {
		return backlogId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	// TODO: Is this method really needed?
	public void addTask(int index, Task task) {
		tasks.add(index, task);
		task.setBacklog(this);
	}

	// TODO: Is this method really needed?
	public void removeTask(Task task) {
		task.setBacklog(null);
		tasks.remove(task);
	}

}
