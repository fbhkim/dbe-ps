package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Visitante;

public class VisitanteDao {

	EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("jkcontrol-persistence-unit");
	EntityManager em = 
			factory.createEntityManager();
	
	public void create(Visitante visitante) {
		
		em.getTransaction().begin();
		em.persist(visitante);
		em.getTransaction().commit();
		
		em.clear();
	}
	
	public List<Visitante> listAll() {
		
		TypedQuery<Visitante> query = 
				em.createQuery("SELECT v FROM Visitante v", Visitante.class);
		
		return query.getResultList();
		
	}
}
