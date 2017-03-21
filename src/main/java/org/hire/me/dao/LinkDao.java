package org.hire.me.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hire.me.model.Link;
import org.springframework.stereotype.Repository;

@Repository
public class LinkDao {
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	public Link findByAlias(String alias){		
		try {
			
			Link link = entityManager.createQuery("select l from Link l where l.alias = :alias ",Link.class)
					.setParameter("alias", alias)
					.getSingleResult();
			
			return link;	
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void salvar(Link link){
		entityManager.persist(link);
	}

	public void updateQtdAcesso(Link link) {
		entityManager.createQuery("update Link set qtdAcesso = :qtdAcesso where alias = :alias ")
			.setParameter("alias", link.getAlias())
			.setParameter("qtdAcesso", link.getQtdAcesso())
			.executeUpdate();
		
	}
	
	public List<Link> getlistarMaxDez() {
		return getlistar(10);
	}

	public List<Link> getlistar(int maxResult) {
		return entityManager.createQuery("select l from Link l order by l.qtdAcesso desc",Link.class)
				.setMaxResults(maxResult)
				.getResultList();
	}
	
	public void removeLink(String alias) {
		entityManager.remove(findByAlias(alias));
	}		
	
	public void removeLink(Link link) {
		entityManager.remove(link);
	}	

}
