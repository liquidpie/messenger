package com.vivek.rest.jersey.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8124106494599385405L;

	public DataNotFoundException(String message) {
		super(message);
	}
}
