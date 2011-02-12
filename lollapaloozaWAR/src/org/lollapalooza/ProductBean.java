package org.lollapalooza;

import java.util.List;
import javax.ejb.EJB;
import org.lollapalooza.entity.Product;
import org.lollapalooza.util.transaction.*;
import org.lollapalooza.util.string.CSVStringList;

public class ProductBean {
	public enum Mode { Add, Edit }; 
		
	@EJB org.lollapalooza.eao.LollapaloozaEao eao;

	private NonIdempotent nonIdempotentController;
	private String transactionId;
	
	private String successMessage;
	private String errorMessage;
	private Mode mode;
	private Product product;
	private List products;
	
	public ProductBean() throws Exception {
		super();
		nonIdempotentController = 
			NonIdempotentTransactionControllerFactory.create("UUID-based");
		transactionId = nonIdempotentController.getNewTransactionId();
		clear();
	}
	
	public void clear() {
		product =  new Product();
		clearMessages();
	}
	
	public void clearMessages() {
		successMessage = null;
		errorMessage = null;
	}
	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}
	
	public void setSelectedProduct(Product selectedProduct) {
		this.product = selectedProduct;
	}
	
	public String getSuccessMessage() {
		return successMessage;
	}
	
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
		this.errorMessage = null;
	}
			
	public String getName() {
		return product.getName();
	}

	public void setName(String name) {
		product.setName(name);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}	

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		this.successMessage = null;
	}
	
	void setAddMode() {
		this.mode = Mode.Add;
	}
	
	void setEditMode() {
		this.mode = Mode.Edit;
	}
	
	public List getProducts() {
		products = eao.getProducts(); 
		return products; 
	}
	
	public String save() {
		try {
			if (mode == Mode.Add) {
				if (!nonIdempotentController.isCommited(transactionId)) {
						// add to db				
						eao.addProduct(product);
						//if (name.equals("xxx")) throw new Exception("Test exception");
						nonIdempotentController.commit(transactionId);						
				}
				
				String addedProductName = product.getName();
				clear();
				setSuccessMessage("Product " + addedProductName + " added.");
			}
			else if (mode == Mode.Edit) {
				if (!nonIdempotentController.isCommited(transactionId)) {
					// edit record in db
					eao.alterProduct(product);
					//if (name.equals("xxx")) throw new Exception("Test exception");
					nonIdempotentController.commit(transactionId);
				}
				
				String addedProductName = product.getName();
				clear();
				setSuccessMessage("Product " + addedProductName + " updated.");
			}
			else
				throw new Exception("Incorrect mode");			
						
			transactionId = nonIdempotentController.getNewTransactionId();
			return "return";
		}
		catch (Exception e) {
			setErrorMessage("Error encountered: " + e.getMessage());
			return "refresh";
		}
	}
	
	public String cancel() {
		clear();
		setErrorMessage("Edit canceled.");
		transactionId = nonIdempotentController.getNewTransactionId();
		return "return";
	}		
		
	public String add() {
		clear();
		setAddMode();
		transactionId = nonIdempotentController.getNewTransactionId();
		return "editProduct";
	}
	
	public String edit() {
		clearMessages();
		setEditMode();
		transactionId = nonIdempotentController.getNewTransactionId();
		return "editProduct";
	}
	
	public String deleteSelected() {
		if (!nonIdempotentController.isCommited(transactionId)) {
			CSVStringList list = new CSVStringList();
			
			if (products != null) {
				for (Object product : products) {
					if (((Product)product).isSelected()) {
						eao.deleteProduct((Product)product);
						list.add("'" + ((Product)product).getName() + "'");
					}
				}
			}
			
			nonIdempotentController.commit(transactionId);			
		
			if (list.size() > 0) {
				if (list.size() == 1)
					setSuccessMessage("Product " + list.toString() + " deleted.");
				else
					setSuccessMessage("Products: " + list.toString() + " deleted.");
			}
		}
		
		transactionId = nonIdempotentController.getNewTransactionId();
		return "refresh";
	}
}
