package org.lollapalooza;

public class ProductBean {
	public enum Mode { Add, Edit }; 
		
	private int id;
	private String name;
	private String successMessage;
	private String errorMessage;
	private Mode mode;
	
	public ProductBean() {
		super();
		clear();
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String save() {
		try {
			if (mode == Mode.Add) {
				// TODO: add to db
				if (name.equals("xxx")) throw new Exception("Test exception");
				
				String addedProductName = name;
				clear();
				setSuccessMessage("Product " + addedProductName + " added.");
			}
			else if (mode == Mode.Edit) {
				// TODO: edit record in db
				if (name.equals("xxx")) throw new Exception("Test exception");
				
				String addedProductName = name;
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
		name = null;
		successMessage = null;
		errorMessage = null;
		id = -1;
		this.mode = Mode.Add;
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
		return id;
	}

	public Mode getMode() {
		return mode;
	}
	
	void setAddMode() {
		clear();
		this.mode = Mode.Add;
	}
	
	void setEditMode(int id) {
		clear();
		this.mode = Mode.Edit;
		this.id = id;
		// TODO: fetch data from db
	}
}
