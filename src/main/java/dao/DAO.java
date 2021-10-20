package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {
	private static final EntityManagerFactory emFactory;

	static {
		emFactory = Persistence.createEntityManagerFactory("aulaprime");
	}

	public static EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public static void fecharFactory() {
		emFactory.close();
	}
}
