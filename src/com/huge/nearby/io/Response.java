package com.huge.nearby.io;

public class Response {

	public Response(String responseContent, int responseCode, String message) {
		this.responseCode = responseCode;
		this.responseContent = responseContent;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseContent() {
		return responseContent;
	}

	private String responseContent;
	private String message;
	private int responseCode;
}
