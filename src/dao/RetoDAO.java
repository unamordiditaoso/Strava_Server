package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import data.domain.Reto;

public class RetoDAO extends DataAccessObjectBase implements IDataAccessObject<Reto>{

	private static RetoDAO instance;
	
	private RetoDAO() { }
	
	public static RetoDAO getInstance() {
		if(instance == null) {
			instance = new RetoDAO();
		}
		return instance;
	}
	
	@Override
	public void guardar(Reto object) {
		Reto storedObject = instance.find(object.getNombre());
		
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
			System.out.println("  $ Error guardando Reto: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
	}

	@Override
	public void borrar(Reto object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Reto storedObject = em.find(Reto.class, object.getNombre());
			em.remove(storedObject);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error eliminando Reto: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
	}

	@Override
	public List<Reto> findAll() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		List<Reto> retos = new ArrayList<>();
		try {
			tx.begin();
			
			Query q = (Query) em.createQuery("SELECT r FROM Reto r");
			
			retos = q.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error buscando Usuarios: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
		
		return retos;
	}

	@Override
	public Reto find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Reto result = null;
		
		try {
			tx.begin();
			
			result = em.find(Reto.class, param);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error buscando Reto por nombre: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
		
		return result;
	}

}