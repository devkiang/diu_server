package com.appserver.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class AppActionBase extends ActionSupport{

	protected static final String OK=SUCCESS;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	public AppActionBase()
	{
		params=request().getParameter("key");
		System.out.println("+++++"+params);
	}
	protected String params;
	private Map<String, Object> responseMap=new HashMap<String, Object>();//装载Json数据
	
	public Map<String, Object> getResponseMap() {
		return responseMap;
	}

	public void setResponseMap(Map<String, Object> jsonMap) {
		this.responseMap = jsonMap;
	}
	
	
	protected  void addSuccessJsonData(Object data)
	{
		this.responseMap.clear();
		this.responseMap.put("code", "1");
		this.responseMap.put("data", data);
	}
	
	
	protected void addErrorJsonData(Object data) {
		this.responseMap.clear();
		this.responseMap.put("code", "-1");
		this.responseMap.put("data", data);
	}
	
	
	protected void addErrorJsonData(String errorCode,Object data) {
		this.responseMap.clear();
		this.responseMap.put("code", errorCode);
		this.responseMap.put("data", data);
	}
	
	protected HttpServletRequest request()
	{
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse response()
	{
		return ServletActionContext.getResponse();
	}
	
}
