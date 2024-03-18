package com.shaw.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends Exception {

	private String resourceNameString;
	private String fieldNameString;
	private Integer fieldValue;

	public ResourceNotFound(String resourceNameString, String fieldNameString, Integer fieldValue) {
		super(resourceNameString + " not find with " + fieldNameString + ": " + fieldValue);
		this.resourceNameString = resourceNameString;
		this.fieldNameString = fieldNameString;
		this.fieldValue = fieldValue;
	}

}
