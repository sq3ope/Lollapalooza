package org.lollapalooza.service;
import javax.ejb.Remote;

@Remote
public interface LollapaloozaInterface {
	public long productCount();
	public void addProduct(String name);
}
