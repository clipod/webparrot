package com.webparrot.webparrot.exceptions;

import java.io.Serializable;

public class BadRequest extends RuntimeException implements Serializable,Cloneable {

	private static final long serialVersionUID = 2539409392853479604L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
