package de.phib.tasket.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task implements Serializable {

	private static final long serialVersionUID = 1475534033312498607L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;

	@ManyToOne
	private Backlog backlog;

	private String title;

	public Task() {
	}

	public Task(String title) {
		this.title = title;
	}

	public int getTaskId() {
		return this.taskId;
	}

	public Backlog getBacklog() {
		return this.backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (!(other instanceof Task)) {
			return false;
		}

		Task otherTask = (Task) other;

		return (otherTask.getTaskId() == this.getTaskId())
				&& (otherTask.getTitle() != null && otherTask.getTitle().equals(this.getTitle()));
	}

	@Override
	public int hashCode() {
		int constant = 37;
		int total = 17;

		total = total * constant + (this.getTaskId() ^ (this.getTaskId() >> 32));
		total = total * constant + this.getTitle().hashCode();

		return total;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + this.taskId + ", backlog=" + this.backlog + ", title=" + this.title + "]";
	}
}
