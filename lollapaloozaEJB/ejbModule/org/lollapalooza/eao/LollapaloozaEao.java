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
        //
    }

    public long countProducts() {
        long result;
        Query q = em.createQuery("select count(co) from Product co");
        result = (Long)q.getSingleResult();
        return result;
    }
    
    public int getNewProductId() {
    	Product product = new Product();
    	product.setIsDeleted("Y");
    	em.persist(product);
    	em.flush();
    	return product.getId();
    }

	/*public void addProduct(String name) {
		Product product =  new Product();
		product.setName(name);
		
		em.persist(product);		
	}*/
	
	public void addProduct(Product product) {
		//em.persist(product);
		product.setIsDeleted("N");
		em.merge(product);
	}
	
	public List getProducts() {
		Query q = em.createQuery("select co from Product co where co.isDeleted = 'N'");
		q.setMaxResults(100);
        return q.getResultList();
	}

	/*public void alterProduct(int id, String name) {
		/*em.getTransaction().begin();
		try {
			Product product = em.find(Product.class, id);
			product.setName(name);
			em.getTransaction().commit();
		}
		finally {
			em.getTransaction().rollback();
		}*//*
		
		Product product = em.find(Product.class, id);
		product.setName(name);
		em.merge(product);
	}*/
	
	public void alterProduct(Product product) {
		em.merge(product);
	}
	
	public Product getProductById(int id) {
		return em.find(Product.class, id);
	}

	public void deleteProduct(Product product) {
		//Product attachedProduct = em.find(Product.class, product.getId());
		//em.remove(em.merge(product));
		product.setIsDeleted("Y");
		em.merge(product);
	}
}
