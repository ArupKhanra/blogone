package com.BlogApp1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
     private String resourceName;
     private String fieldName;
     private long filedValue;
     
     public ResourceNotFoundException(String resourceName,String fieldName,long id) {
    	 super(String.format("%s not fount with %s : '%s'", resourceName,fieldName,id));
    	 this.resourceName=resourceName;
    	 this.fieldName=fieldName;
    	 this.filedValue=id;
     }

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public long getFiledValue() {
		return filedValue;
	}
     
}
