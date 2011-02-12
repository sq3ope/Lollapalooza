package org.lollapalooza.util.string;

import java.util.ArrayList;

public class CSVStringList {
	private ArrayList<String> list;
	
	public CSVStringList() {
		list = new ArrayList<String>();
	}
	
	public void add(String s) {
		list.add(s);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for (String s : list) {
			if (sb.length() == 0)
				sb.append(s);
			else
				sb.append(',' + s);
		}
		
		return sb.toString();
	}
	
	public int size() {
		return list.size();
	}
}
