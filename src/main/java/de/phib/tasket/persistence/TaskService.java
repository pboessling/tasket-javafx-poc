package de.phib.tasket.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TaskService {

	private EntityManager entityManager;

	public TaskService() {
		this.entityManager = PersistenceUtil.getEntityManager();
	}

	public Task create(String title) {
		entityManager.getTransaction().begin();

		Task task = new Task();
		task.setTitle(title);

		entityManager.persist(task);
		entityManager.getTransaction().commit();

		return task;
	}

	public void delete(Task task) {
		if (task != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(task);
			entityManager.getTransaction().commit();
		}
	}

	public Task findByTaskId(int taskId) {
		return entityManager.find(Task.class, taskId);
	}

	@SuppressWarnings("unchecked")
	public List<Task> findAll() {
		Query query = entityManager.createQuery("SELECT t FROM Task t");
		return query.getResultList();
	}

}
