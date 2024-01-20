package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import data.domain.Entrenamiento;


public class SesionDAO extends DataAccessObjectBase implements IDataAccessObject<Entrenamiento>{

	private static SesionDAO instance;
	
	private SesionDAO() { }
	
	public static SesionDAO getInstance() {
		if(instance == null) {
			instance = new SesionDAO();
		}
		return instance;
	}
	
	@Override
	public void guardar(Entrenamiento object) {
		Entrenamiento storedObject = instance.find(object.getTitulo());
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			if (storedObject != null) {
				em.merge(object);
			} else {
				em.persist(object);
			}
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error guardando Entrenamiento: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
	}

	@Override
	public void borrar(Entrenamiento object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Entrenamiento storedObject = em.find(Entrenamiento.class, object.getTitulo());
			em.remove(storedObject);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error eliminando Entrenamiento: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
	}

	@Override
	public List<Entrenamiento> findAll() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		List<Entrenamiento> sesiones = new ArrayList<>();
		
		try {
			tx.begin();
			
			Query q = (Query) em.createQuery("SELECT s FROM Entrenamiento s");
			
			sesiones = q.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error buscando Entrenamientos: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
		
		return sesiones;
	}

	@Override
	public Entrenamiento find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Entrenamiento result = null;
		
		try {
			tx.begin();
			
			result = em.find(Entrenamiento.class, param);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error buscando Entrenamiento por titulo: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
		
		return result;
	}

}
