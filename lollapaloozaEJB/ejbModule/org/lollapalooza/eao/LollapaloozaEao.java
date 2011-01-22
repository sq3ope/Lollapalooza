package org.lollapalooza.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.lollapalooza.entity.Product;

/**
 * Session Bean implementation class LollapaloozaEao
 */
@LocalBean
@Stateless
public class LollapaloozaEao {
	@PersistenceContext
    EntityManager em;

    /**
     * Default constructor. 
     */
    public LollapaloozaEao() {
        // TODO Auto-generated constructor stub
    }

    public long countProducts() {
        long result;
        Query q = em.createQuery("select count(co) from Product co");
        result = (Long)q.getSingleResult();
        return result;
    }

	public void addProduct(String name) {
		Product product =  new Product();
		product.setName(name);
		
		em.persist(product);		
	}
	
	public List getProducts() {
		Query q = em.createQuery("select co from Product co");
		q.setMaxResults(100);
        return q.getResultList();
	}
}
