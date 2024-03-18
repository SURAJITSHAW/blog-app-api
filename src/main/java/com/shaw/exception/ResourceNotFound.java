package com.shaw.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFound extends Exception {

	private String resourceNameString;
	private String fieldNameString;
	private Integer fieldValue;

	@Override
    public String getMessage() {
        return String.format("%s not found with %s: %s", resourceNameString, fieldNameString, fieldValue);
    }

}
