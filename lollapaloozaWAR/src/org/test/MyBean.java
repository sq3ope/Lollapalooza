package org.test;

public class MyBean {
	private String input;
	private String output;
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	public String action() {
		output = "Helllo " + input + "!";
		return output;
	}
}
