package com.huge.nearby.foursquare;

public class ResultMeta {
	
	private Integer code;
	private String errorType;
	private String errorDetail;

	public ResultMeta(Integer code, String errorType, String errorDetail) {
		this.code = code;
		this.errorType = errorType;
		this.errorDetail = errorDetail;
	}

	public Integer getCode() {
		return code;
	}

	public String getErrorType() {
		return errorType;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

;
}