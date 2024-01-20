package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import data.domain.Usuario;

public class UsuarioDAO extends DataAccessObjectBase implements IDataAccessObject<Usuario>{

	private static UsuarioDAO instance;
	
	private UsuarioDAO() { }
	
	public static UsuarioDAO getInstance() {
		if(instance == null) {
			instance = new UsuarioDAO();
		}
		return instance;
	}
	
	@Override
	public void guardar(Usuario object) {
		Usuario storedObject = instance.find(object.getCorreo());
		
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
			System.out.println("  $ Error guardando Usuario: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
	}

	@Override
	public void borrar(Usuario object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Usuario storedObject = em.find(Usuario.class, object.getCorreo());
			em.remove(storedObject);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error borrando Usuario: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
	}

	@Override
	public List<Usuario> findAll() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		List<Usuario> usuarios = new ArrayList<>();
		try {
			tx.begin();
			
			Query q = (Query) em.createQuery("SELECT u FROM Usuario u");
			
			usuarios = q.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error buscando Usuarios: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
		
		return usuarios;
	}

	@Override
	public Usuario find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Usuario result = null;
		
		try {
			tx.begin();
			
			result = em.find(Usuario.class, param);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println("  $ Error buscando Usuario por Email: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			em.close();
		}
		
		return result;
		
	}

}