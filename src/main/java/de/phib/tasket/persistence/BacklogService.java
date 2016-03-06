package de.phib.tasket.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BacklogService {

	private EntityManager entityManager;

	public BacklogService() {
		this.entityManager = PersistenceUtil.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<Backlog> findAllBacklogs() {
		Query query = entityManager.createNamedQuery(Backlog.QUERY_FIND_ALL_BACKLOGS);
		return query.getResultList();
	}

	public Backlog createBacklog(String title) {
		entityManager.getTransaction().begin();

		Backlog backlog = new Backlog();
		backlog.setTitle(title);

		entityManager.persist(backlog);
		entityManager.getTransaction().commit();

		return backlog;
	}

	public Backlog updateBacklog(Backlog backlog) {
		Backlog updatedBacklog = null;

		if (backlog != null) {
			entityManager.getTransaction().begin();
			updatedBacklog = entityManager.merge(backlog);
			entityManager.getTransaction().commit();
		}

		return updatedBacklog;
	}

	public void deleteBacklog(Backlog backlog) {
		if (backlog != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(backlog);
			entityManager.getTransaction().commit();
		}
	}

}
