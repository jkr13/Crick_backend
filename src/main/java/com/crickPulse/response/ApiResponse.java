package com.crickPulse.response;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.crickPulse.exception.LowerCaseClassNameResolver;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import lombok.Data;

/**
 * ApiResponse use to set main response of controller
 * 
 * @author inkal
 *
 */
@Data

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "response", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiResponse {

	private List<Object> dataArray;

	private Object data;

	private long timestamp;

	private ApiResponse() {
		timestamp = new Date().getTime();
	}

	public ApiResponse(Object object) {
		this();
		this.data = object;
	}

	public ApiResponse(JSONObject object) {
		this();
		this.data = object;
	}

	public ApiResponse(JSONArray object) {
		this();
		this.data = object;
	}

	public ApiResponse(List<Object> objects) {
		this();
		this.dataArray = objects;
	}

	public ApiResponse(Object object, List<Object> objects) {
		this.data = object;
		this.dataArray = objects;
	}

}
