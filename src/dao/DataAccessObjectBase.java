package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import data.domain.Usuario;

public class DataAccessObjectBase {
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("STRAVA");
	
	public void borrar(Object object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.remove(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println(String.format(" $ Error eliminando un objeto(%s): %s", object.toString(), ex.getMessage()));
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
		
	}
	
	public void guardar(Object object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println(String.format(" $ Error eliminando un objeto(%s): %s", object.toString(), ex.getMessage()));
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
	}

	
}
