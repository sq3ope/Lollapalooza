package org.lollapalooza;

import java.util.List;
import javax.ejb.EJB;
import org.lollapalooza.entity.Product;

public class ProductBean {
	public enum Mode { Add, Edit }; 
		
	@EJB org.lollapalooza.eao.LollapaloozaEao eao;
	
	private String successMessage;
	private String errorMessage;
	private Mode mode;
	private Product product;
	private List products;
	
	public void setSelectedProduct(Product selectedProduct) {
		this.product = selectedProduct;
	}

	public ProductBean() {
		super();
		clear();
	}	
	
	public String getName() {
		return product.getName();
	}

	public void setName(String name) {
		product.setName(name);
	}
	
	public String save() {
		try {
			if (mode == Mode.Add) {
				// add to db				
				eao.addProduct(product);
				//if (name.equals("xxx")) throw new Exception("Test exception");
				
				String addedProductName = product.getName();
				clear();
				setSuccessMessage("Product " + addedProductName + " added.");
			}
			else if (mode == Mode.Edit) {
				// edit record in db
				eao.alterProduct(product);
				//if (name.equals("xxx")) throw new Exception("Test exception");
				
				String addedProductName = product.getName();
				clear();
				setSuccessMessage("Product " + addedProductName + " updated.");
			}
			else
				throw new Exception("Incorrect mode");			
						
			return "save";
		}
		catch (Exception e) {
			setErrorMessage("Error encountered: " + e.getMessage());
			return "error";
		}
	}
	
	public String cancel() {
		clear();
		return "cancel";
	}

	public void clear() {
		Product product =  new Product();
		product.setId(-1);
		successMessage = null;
		errorMessage = null;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
		this.errorMessage = null;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		this.successMessage = null;
	}

	public int getId() {
		return product.getId();
	}

	public Mode getMode() {
		return mode;
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
	
	public String add() {
		clear();
		setAddMode();
		return "addProduct";
	}
	
	public String edit() {
		clear();
		setEditMode();
		return "editProduct";
	}
	
	public String deleteSelected() {
		if (products != null) {
			for (Object product : products) {
				if (((Product)product).isSelected()) {
					eao.deleteProduct((Product)product);
				}
			}
		}
		
		return "deleteProduct";
	}
}
