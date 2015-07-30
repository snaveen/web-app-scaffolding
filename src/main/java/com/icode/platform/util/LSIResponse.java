package com.icode.platform.util;

import java.util.HashMap;
import java.util.Map;

public class LSIResponse {

	private Map<String,Object> data;
	
	private Exception errors;

	public LSIResponse(){
		setData(new HashMap<String, Object>());
	}
		
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void putMessage(String name, Object message){
		data.put(name, message);
	}
	
	public boolean removeMessage(String name){
		if (data.remove(name) != null) { 
			return true;
		} else {
			return false;
		}
	}

	public Exception getErrors() {
		return errors;
	}

	public void setErrors(Exception errors) {
		this.errors = errors;
	}
}