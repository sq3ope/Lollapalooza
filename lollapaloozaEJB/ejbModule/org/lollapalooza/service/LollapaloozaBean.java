package org.lollapalooza.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.lollapalooza.eao.LollapaloozaEao;
import org.lollapalooza.entity.Product;

/**
 * Session Bean implementation class LollapaloozaBean
 */
@LocalBean
@Stateless
@WebService
public class LollapaloozaBean implements LollapaloozaInterface {
	@EJB LollapaloozaEao eao;

    /**
     * Default constructor. 
     */
    public LollapaloozaBean() {
        //
    }

	@Override
	public long productCount() {
		return eao.countProducts();
	}

	@Override
	public void addProduct(String name) {
		//TODO addProduct() EAO
		//eao.addProduct(name);		
	}

	public List<Product> getProducts() {
		//TODO getProducts() EAO
		//return eao.getProducts();
		return null;
	}
}
