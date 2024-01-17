package com.example.demo.entity;

import java.io.Serializable;

import org.json.simple.JSONObject;

public class CustomResponse implements Serializable{
	private JSONObject response;
	
//	public CustomResponse() {}
	public CustomResponse(JSONObject response) {
		this.response=response;
	}
	
	public JSONObject getResponse() {
		return response;
	}
	
	public void setResponse(JSONObject response) {
		this.response=response;
	}

}
