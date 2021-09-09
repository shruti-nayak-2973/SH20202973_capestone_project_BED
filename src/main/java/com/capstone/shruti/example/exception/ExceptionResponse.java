package com.capstone.shruti.example.exception;

public class ExceptionResponse {
String errorMessage;
String requestUri;

public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
public String getRequestUri() {
	return requestUri;
}
public void setRequestUri(String requestUri) {
	this.requestUri = requestUri;
}
}


