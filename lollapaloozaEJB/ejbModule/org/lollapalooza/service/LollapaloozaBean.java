package org.lollapalooza.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.lollapalooza.eao.LollapaloozaEao;

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
        // TODO Auto-generated constructor stub
    }

	@Override
	public long productCount() {
		return eao.countProducts();
	}

	@Override
	public void addProduct(String name) {
		eao.addProduct(name);		
	}

}
