package de.phib.tasket.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

	private static final String PERSISTENCE_UNIT_NAME = "tasketPersistenceUnit";

	private static EntityManager entityManager;

	public static void setUp() {
		String userHomeDir = System.getProperty("user.home", ".");
		String systemDir = userHomeDir + "/.tasket";
		System.setProperty("derby.system.home", systemDir);

		PersistenceUtil.entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager();
	}

	public static void tearDown() {
		EntityManagerFactory factory = PersistenceUtil.entityManager.getEntityManagerFactory();
		PersistenceUtil.entityManager.close();
		factory.close();
	}

	public static EntityManager getEntityManager() {
		return PersistenceUtil.entityManager;
	}

}
