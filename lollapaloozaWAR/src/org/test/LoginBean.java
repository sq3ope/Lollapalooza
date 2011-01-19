package org.test;

public class LoginBean {
	public LoginBean() {
		super();
		this.password = new ComplexPassword();
	}

	String name;
	ComplexPassword password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ComplexPassword getPassword() {
		return password;
	}

	public void setPassword(ComplexPassword password) {
		this.password = password;
	}
}
