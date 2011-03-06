package org.lollapalooza;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.lollapalooza.entity.Product;
import org.lollapalooza.util.transaction.*;
import org.lollapalooza.util.string.CSVStringList;

import com.sun.enterprise.security.jauth.callback.PrivateKeyCallback.Request;

public class ProductBean {
	private String successMessage;
	private String errorMessage;
	private Product product;
	private List products;
	private Integer productId;
	
	@EJB org.lollapalooza.eao.LollapaloozaEao eao;
	
	public ProductBean() throws Exception {
		super();
	
		// parse GET parameters
		
		HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance()
			.getExternalContext().getRequest();
		String productIdS= request.getParameter("productId");
		if (productIdS != null)
			productId = Integer.parseInt(productIdS);
		else
			productId = null;
	}
	
	private void getProduct() {
		if (product == null) {
			if (productId != null)
				product = eao.getProductById(productId);
			else
				product = new Product();
		}
	}
		
	public void clearMessages() {
		successMessage = null;
		errorMessage = null;
	}
	
	/*public void setSelectedProduct(Product selectedProduct) {
		this.product = selectedProduct;
	}*/
	
	public String getSuccessMessage() {
		return successMessage;
	}
	
	public void setSuccessMessage(String successMessage) {
		//this.successMessage = successMessage;
		//this.errorMessage = null;
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				errorMessage, errorMessage);
        FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	public Integer getId() {
		getProduct();
		return product.getId();
	}

	public void setId(Integer id) {
		getProduct();
		product.setId(id);
	}
	
	public String getDeleted() {
		getProduct();
		return product.getIsDeleted();
	}

	public void setDeleted(String deleted) {
		getProduct();
		product.setIsDeleted(deleted);
	}
			
	public String getName() {
		getProduct();
		return product.getName();
	}

	public void setName(String name) {
		getProduct();
		product.setName(name);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}	

	public void setErrorMessage(String errorMessage) {
		//this.errorMessage = errorMessage;
		//this.successMessage = null;
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				errorMessage, errorMessage);
        FacesContext.getCurrentInstance().addMessage(null, fm);
	}
		
	public List getProducts() {
		products = eao.getProducts(); 
		return products; 
	}
	
	public String save() {
		try {
			if (product.getIsDeleted().equals("Y")) { // add
				eao.addProduct(product);
				
				String addedProductName = product.getName();
				setSuccessMessage("Product " + addedProductName + " added.");
			}
			else if (product.getIsDeleted().equals("N")) { //edit
				eao.alterProduct(product);
								
				String addedProductName = product.getName();
				setSuccessMessage("Product " + addedProductName + " updated.");
			}
			else
				throw new Exception("Incorrect mode");			
						
			return "return";
		}
		catch (Exception e) {
			setErrorMessage("Error encountered: " + e.getMessage());
			return "error";
		}
	}
	
	public String cancel() {
		setErrorMessage("Edit canceled.");
		return "return";
	}		
		
	public String add() {
		try {
			Integer id = eao.getNewProductId();
			FacesContext.getCurrentInstance().getExternalContext().
				redirect("ProductEdit.jsf?productId=" + id.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "editProduct";
	}
	
	public String edit() {
		clearMessages();
		return "editProduct";
	}
	
	public String deleteSelected() {
		CSVStringList list = new CSVStringList();
		
		if (products != null) {
			for (Object product : products) {
				if (((Product)product).isSelected()) {
					eao.deleteProduct((Product)product);
					list.add("'" + ((Product)product).getName() + "'");
				}
			}
		}
		
	
		if (list.size() > 0) {
			if (list.size() == 1)
				setSuccessMessage("Product " + list.toString() + " deleted.");
			else
				setSuccessMessage("Products: " + list.toString() + " deleted.");
		}
				
		return "delete";
	}
}
